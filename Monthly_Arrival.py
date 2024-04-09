import psycopg2
import csv
import pandas as pd
import plotly.express as px
import matplotlib.pyplot as plt

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
            print("Column names from CSV:", columns)

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

#create_insert_table('student-visa-arrivals.csv', 'Monthly_Arrivals')

df = fetch_data('Monthly_Arrivals')

first_column_name = df.columns[0]

new_column_name = "Month"
df.rename(columns={first_column_name: new_column_name}, inplace=True)

# Convert columns to float
for col in df.columns[1:]:
    df[col] = pd.to_numeric(df[col], errors='coerce')

print(df.head())

# Convert columns to float
for col in df.columns[1:]:
    df[col] = pd.to_numeric(df[col], errors='coerce')

# Plotting with Plotly Express
fig = px.line(df, x='Month', y=df.columns[1:], title='Data Trends Over Months')

# Update layout to set y-axis limits
fig.update_yaxes(range=[0, 200000], tickvals=list(range(0, 200001, 10000)))

# Update hover behavior
fig.update_traces(mode='lines+markers', hoverinfo='y')

# Update aesthetics and enlarge graph
fig.update_layout(
    autosize=False,
    width=1000,  # Enlarged width
    height=600,  # Enlarged height
    xaxis=dict(title='Month', tickangle=45),
    yaxis=dict(title='Data'),
    title=dict(x=0.5),
    legend=dict(title='Year')
)

# Show plot
fig.show()