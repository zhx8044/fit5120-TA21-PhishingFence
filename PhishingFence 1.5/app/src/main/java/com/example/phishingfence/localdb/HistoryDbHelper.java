package com.example.phishingfence.localdb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.phishingfence.model.HistoryInfo;
import com.example.phishingfence.ui.fragments.HistoryFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryDbHelper extends SQLiteOpenHelper {
    private static HistoryDbHelper sHelper;
    private static final String DB_NAME = "news_history.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public HistoryDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static HistoryDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new HistoryDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table news_history(history_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "title text," +
                "detailUrl text," +
                "newsImage int,"+
                "viewed_date DATETIME" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int addHistory(String username,String title,String detailUrl,int newsImage) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        //填充占位符
        values.put("username", username);
        values.put("title", title);
        values.put("detailUrl", detailUrl);
        values.put("newsImage",newsImage);
        values.put("viewed_date",currentDate);
        String nullColumnHack = "values(null,?,?,?,?,?)";
        //执行
        int insert = (int) db.insert("news_history", nullColumnHack, values);
        db.close();
        return insert;
    }

    public int delete(String history_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("news_history", " history_id=?", new String[]{String.valueOf(history_id)});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    public boolean updateHistory(String username, String title, String detailUrl, int newsImage) {
        // 获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 定义查询条件，检查所有字段是否匹配
        String whereClause = "username=? AND title=? AND detailUrl=? AND newsImage=?";
        String[] whereArgs = {username, title, detailUrl, String.valueOf(newsImage)};

        // 执行查询
        Cursor cursor = db.query("news_history", null, whereClause, whereArgs, null, null, null);

        // 检查是否存在记录
        boolean exists = cursor.getCount() > 0;

        if (exists) {
            // 如果存在，更新这些记录的浏览时间
            // 使用Java获取当前时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentDate = dateFormat.format(new Date());

            ContentValues values = new ContentValues();
            values.put("viewed_date", currentDate);
            db.update("news_history", values, whereClause, whereArgs);
        }

        // 关闭游标和数据库连接
        cursor.close();
        db.close();

        return exists;
    }

    public void updateOrAddHistory(String username, String title, String detailUrl, int newsImage) {
        // 尝试更新存在的历史记录的浏览时间
        boolean updated = updateHistory(username, title, detailUrl, newsImage);

        if (!updated) {
            // 如果没有找到记录进行更新，则添加新的历史记录
            addHistory(username, title, detailUrl, newsImage);
        }
    }

    public List<HistoryInfo> findAllHistoryByUserAndMaintain(String username) {
        List<HistoryInfo> histories = new ArrayList<>();
        // 获取SQLiteDatabase实例
        SQLiteDatabase db = this.getReadableDatabase();

        // 查询所有匹配的历史记录，按照viewed_date降序排序
        String sql = "SELECT * FROM news_history WHERE username = ? ORDER BY viewed_date DESC";
        Cursor cursor = db.rawQuery(sql, new String[]{username});

        // 用来存放需要删除的history_id
        List<Integer> idsToDelete = new ArrayList<>();

        // 遍历查询结果
        while (cursor.moveToNext()) {
            // 如果历史记录数量超过20，则记录超出部分的history_id用于之后删除
            if (histories.size() >= 20) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("history_id"));
                idsToDelete.add(id);
                continue; // 不再向列表添加新的HistoryInfo对象
            }

            @SuppressLint("Range") int historyId = cursor.getInt(cursor.getColumnIndex("history_id"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String detailUrl = cursor.getString(cursor.getColumnIndex("detailUrl"));
            @SuppressLint("Range") int newsImage = cursor.getInt(cursor.getColumnIndex("newsImage"));
            @SuppressLint("Range") String viewedDate = cursor.getString(cursor.getColumnIndex("viewed_date"));

            // 创建HistoryInfo对象并添加到列表中
            histories.add(new HistoryInfo(historyId, username, title, detailUrl, newsImage, viewedDate));
        }

        // 关闭游标
        cursor.close();

        // 如果有超出部分，则从数据库中删除这些记录
        if (!idsToDelete.isEmpty()) {
            String ids = TextUtils.join(", ", idsToDelete); // 将需要删除的ID转换为逗号分隔的字符串
            db.execSQL("DELETE FROM news_history WHERE history_id IN (" + ids + ")");
        }

        // 关闭数据库连接
        db.close();

        return histories;
    }
}
