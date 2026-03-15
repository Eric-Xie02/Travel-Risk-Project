import csv

input_file = "airports.csv"
output_file = "../src/main/resources/data.sql"

with open(input_file, newline='', encoding='utf-8') as csv_file:
    reader = csv.DictReader(csv_file)
    with open(output_file, 'w', encoding='utf-8') as sql_file:
        for row in reader:
            name = row['name'].replace("'", "''")
            iataCode = row['iata_code'].replace("'", "''")
            country = row['iso_country'].replace("'", "''")

            if not iataCode: continue

            sql = f"INSERT INTO airports (name, iata_code, iso_country) VALUES ('{name}', '{iataCode}', '{country}');\n"
            sql_file.write(sql)

print("Done")