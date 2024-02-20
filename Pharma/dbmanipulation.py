import sqlite3
import os

# Check if the database file exists before connecting to it
if not os.path.exists('patients.db'):
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    # Create the 'patients' table if it doesn't exist
    cursor.execute("""CREATE TABLE patients (
        first_name TEXT,
        starting_year INTEGER,
        starting_month INTEGER,
        starting_day INTEGER
    )""")

    conn.commit()
    conn.close()


class patient:
    def __init__(self, name, day, year, month):
        self.name = name
        self.year = year
        self.day = day
        self.month = month


# Define a function to add a patient to the database
def savePatientToDB(patient):
    patientName = patient.name
    patientYear = patient.year
    patientMonth = patient.month
    patientDay = patient.day

    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    # Insert the patient's information into the 'patients' table
    cursor.execute("INSERT INTO patients (first_name, starting_year, starting_month, starting_day) VALUES (?, ?, ?, ?)",
                   (patientName, patientYear, patientMonth, patientDay))

    conn.commit()
    conn.close()

def remove_patient(patientName):
    
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    # Insert the patient's information into the 'patients' table
    cursor.execute("DELETE FROM patients WHERE first_name = ?",
                   (patientName,))

    conn.commit()
    conn.close()

def getNames():
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    cursor.execute("SELECT first_name FROM patients")
    rows = cursor.fetchall()
    conn.close()
    return rows

def allInfo():
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    cursor.execute("SELECT * FROM patients")
    rows = cursor.fetchall()
    conn.close()
    return rows
   
def updateUser(patient):
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()

    year = patient.year
    month = patient.month
    day = patient.day
    name = patient.name

    cursor.execute(""" 
                   UPDATE patients 
                   SET starting_year = ?, starting_month = ?, starting_day = ? 
                   WHERE first_name = ? 
                   """, (year, month, day, name))
    conn.commit()
    conn.close()

def getPerson(name):
    conn = sqlite3.connect('patients.db')
    cursor = conn.cursor()
    cursor.execute(""" SELECT starting_year, starting_month, starting_day FROM patients
                   WHERE first_name = ?
                   """, (name,))
    rows = cursor.fetchall()
    conn.close()
    return rows
