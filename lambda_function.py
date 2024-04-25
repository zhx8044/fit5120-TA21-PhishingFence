import json
import boto3
import joblib
from io import BytesIO

# 指定您的 S3 存储桶和模型文件名
BUCKET_NAME = 'fit5120ta21'
MODEL_FILE_NAME = 'spam_detection_pipeline.pkl'

# 初始化 S3 客户端
s3 = boto3.client('s3')

def lambda_handler(event, context):
    # 从 S3 存储桶中获取模型文件
    response = s3.get_object(Bucket=BUCKET_NAME, Key=MODEL_FILE_NAME)
    model_str = response['Body'].read()
    # 使用 joblib 加载您的模型
    loaded_pipeline = joblib.load(BytesIO(model_str))

    # 假设 event['body'] 包含要预测的文本消息
    input_message = event['body'] if 'body' in event else ''
    normalized_message = input_message.replace('\n', ' ')

    # 输出规范化后的消息
    print("Normalized Message:", normalized_message)

    # 使用管道预测新消息的类别
    predicted_class = loaded_pipeline.predict([normalized_message])[0]

    # 获取每个类别的概率
    probabilities = loaded_pipeline.predict_proba([normalized_message])[0]

    # 构造响应对象
    return {
        'statusCode': 200,
        'body': json.dumps({
            'Predicted class': predicted_class,
            'Probability of being ham': probabilities[0],
            'Probability of being spam': probabilities[1]
        })
    }
