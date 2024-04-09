import psycopg2
import csv
import pandas as pd
import plotly.express as px

# Database connection parameters
dbname = "spam_app"
user = "rohan"
password = "oL65wv1hroU1mI62SGk5_w"
host = "lotus-knight-6173.6xw.aws-ap-southeast-1.cockroachlabs.cloud"
port = "26257"
sslmode = "require"

# Construct the connection string
conn_string = f"dbname='{dbname}' user='{user}' password='{password}' host='{host}' port='{port}' sslmode='{sslmode}'"

# Function to Create and Inset values in Database
def create_insert_table(csvfile,tableName):
    try:
        # Connect to the uv2 database
        conn = psycopg2.connect(conn_string)
        conn.autocommit = True

        # Create a cursor
        cursor = conn.cursor()
        # Read data from the CSV file
        with open(csvfile, 'r', newline='') as file:
            reader = csv.reader(file)

            # Read the first row for column names
            columns = next(reader)

            # Create the book table dynamically
            create_table_query = f"""
            CREATE TABLE IF NOT EXISTS {tableName} (
                {", ".join(f'"{column.strip()}" TEXT' for column in columns)}
            );
            """
            cursor.execute(create_table_query)

            # Insert data into the table
            for row in reader:
                insert_query = f"""
                INSERT INTO {tableName} ({", ".join(f'"{column.strip()}"' for column in columns)}) 
                VALUES ({', '.join(['%s']*len(row))});
                """
                cursor.execute(insert_query, row)

        print("Data inserted successfully.")

        # Close the cursor and connection
        cursor.close()
        conn.close()

    except psycopg2.OperationalError as e:
        print("Unable to connect to the database:", e)
    except psycopg2.Error as e:
        print("Error occurred while executing SQL query:", e)


## Fetch Data
def fetch_data(tableName):
    try:
        # Connect to the uv2 database
        conn = psycopg2.connect(conn_string)
        conn.autocommit = True

        # Create a cursor
        cursor = conn.cursor()

        # Execute SELECT query to retrieve all data from the book table
        cursor.execute("SELECT * FROM public." + tableName + ";")

        # Fetch all rows
        rows = cursor.fetchall()

        # Get column names from cursor description
        columns = [description[0] for description in cursor.description]

        # Create pandas DataFrame
        df = pd.DataFrame(rows, columns=columns)

        # Close the cursor and connection
        cursor.close()
        conn.close()

    except psycopg2.OperationalError as e:
        print("Unable to connect to the database:", e)

    except psycopg2.Error as e:
        print("Error occurred while executing SQL query:", e)

    return df

#create_insert_table('Diverse_International_Data.csv', 'Diverse_International_Student')

df = fetch_data('Diverse_International_Student')

# Select top 10 nations
top_10 = df.head(10)

# Calculate the total population of the nations beyond the top 10
other_population = df.iloc[10:]['Total'].sum()

# Append 'Others' to the DataFrame
others = pd.DataFrame({"Nationality": ["Others"], "Total": [other_population]})
top_10_and_others = pd.concat([top_10, others])

# Create pie chart
fig = px.pie(top_10_and_others, values='Total', names='Nationality', title='Top 10 Nations and Others')

# Customizing layout
fig.update_layout(
    title={
        'text': "Total Population by Nationality",
        'y': 0.95,
        'x': 0.5,
        'xanchor': 'center',
        'yanchor': 'top'},
    font=dict(family="Arial, sans-serif", size=12, color="#7f7f7f"),
    margin=dict(l=20, r=20, t=50, b=20),
    legend=dict(
        title=None,
        orientation="h",
        yanchor="bottom",
        y=1.02,
        xanchor="right",
        x=1,
        font=dict(
            family="Arial, sans-serif",
            size=10,
            color="black"
        )
    )
)

# Show plot
fig.update_traces(textposition='inside', textinfo='label+percent')
fig.show()

