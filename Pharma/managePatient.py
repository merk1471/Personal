from tkinter import ttk
from tkinter import *
from dbmanipulation import *
from datetime import datetime, timedelta

def updateUsers():
    generalData = allInfo()
    for row in generalData:
        # Extracting data from each row
        name = row[0]
        month = row[2]
        year = row[1]  # Ensure that year is extracted correctly
        day = row[3]   # Ensure that day is extracted correctly

        if (type(month) != int):
            month = getMonthNumber(month)
        
        # Calculate the new date
        previousDate = datetime(year, month, day)  # Convert to datetime object
        currentDate = datetime(timeInfo("year"), timeInfo("month"), timeInfo("day"))
        
        while previousDate < currentDate:
            previousDate += timedelta(weeks=4)  # Increment by 4 weeks
            print(previousDate)
        
        # Extract year, month, and day from the new date
        new_year = previousDate.year
        new_month = previousDate.month
        new_day = previousDate.day

        newPerson = patient(name, new_day, new_year, new_month)
        
        # Update the user in the database
        updateUser(newPerson)
        
    
def getMonthNumber(month):

    months = {"January" : 1 , "Febuary" : 2, "March" : 3, "April" : 4, "May" : 5,
               "June" : 6, "July" : 7, "August" : 8, "September" : 9, "October" : 10,
               "November" : 11, "December" : 12}
    if (month in months):
        return months[month]
    else:
        pass

def addingProcess():
    #Adding window
    addWindow = Toplevel()
    addWindow.title("Add Patient")
    addWindow.geometry('250x250')

    #Options box
    monthFrame = ttk.Frame(addWindow)
    monthFrame.pack()

    #Name box
    nameLabel = ttk.Label(monthFrame, text='Enter Full Name')
    nameLabel.pack()
    nameBox = ttk.Entry(monthFrame)
    nameBox.pack()

    #Day box
    dayLabel = ttk.Label(monthFrame, text='Select Starting Day')
    dayLabel.pack()

    day = ttk.Combobox(monthFrame)
    days = []
    for i in range(1,32):
        days.append(f'{i}')
    day['values'] = tuple(days)
    day.state(["readonly"])
    day.pack()

    #Month box
    monthLabel = ttk.Label(monthFrame, text='Select Starting Month')
    monthLabel.pack()

    month = ttk.Combobox(monthFrame)
    month['values'] = ('January', 'Febuary', 'March', 'April', 'May', 'June', 'July', 'September', 'October', 'November', 'December')
    month.state(["readonly"])
    month.pack()

    #Year box
    yearLabel = ttk.Label(monthFrame, text='Select Starting Year')
    yearLabel.pack()

    year = ttk.Combobox(monthFrame)
    year['values'] = ('2023', '2024')
    year.state(["readonly"])
    year.pack()

    #Create a new patient and add them to the DB
    def addPatient(name, year, month, day):
        newPerson = patient(name, day, year, month)
        savePatientToDB(newPerson)
        updateUsers()

    #Save the patient
    def saveInfo():
        patientName = nameBox.get()
        patientDay = day.get()
        patientYear = year.get()
        patientMonth = month.get()
        
        if patientName and patientDay and patientYear and patientMonth:
            addPatient(patientName, patientYear, patientMonth, patientDay)
            addWindow.destroy()
        else:
            pass


    addPatentBtn = Button(addWindow, text='Save Patient', command=saveInfo)
    addPatentBtn.pack()

def removeProcess():
    removeWindow = Toplevel()
    removeWindow.geometry('450x500')
    removeWindow.title('Remove Patient')

    removeLabel = Label(removeWindow, text='Select a patient to remove')
    removeLabel.pack()
    
    patientFrame = Frame(removeWindow)
    patientFrame.pack()

    patientsList = []
    allPatients = getNames()
    for i in allPatients:
        patientName = str(i)
        firstIndex = patientName.find("'")
        secondIndex = patientName.find("'", firstIndex + 1)
        patientsList.append(patientName[firstIndex + 1:secondIndex])

    removeSelector = ttk.Combobox(patientFrame)
    removeSelector['values'] = tuple(patientsList)
    removeSelector.pack()

    def removePatient():
        patientName = removeSelector.get()
        remove_patient(patientName)
        removeWindow.destroy()

    removeButton = Button(removeWindow, text='Remove Selected Patient', command=removePatient)
    removeButton.pack()

def calculateTime():
    Time = datetime.now()
    weeksinfuture = Time + timedelta(weeks=4)
    print(weeksinfuture)

def timeInfo(option):
    Time = (datetime.now())
    if (option == "month"):
        return Time.month
    elif (option == "day"):
        return Time.day
    elif (option == "year"):
        return Time.year

def getWeeklyPatients(option):
    day = timeInfo("day")

    week1 = []
    week2 = []
    week3 = []
    week4 = []

    generalData = allInfo()
    for i in (generalData):
        patientDay = i[3]
        if(patientDay < 7):
            week1.append(i)
        elif(patientDay < 14):
            week2.append(i)
        elif (patientDay < 21):
            week3.append(i)
        else:
            week4.append(i)

    if (option == "current"):
        if(day < 7):
            return week1
        elif(day < 14):
            return week2
        elif(day < 21):
            return week3
        else:
            return week4
    if (option == "week1"):
        return week1
    elif (option == "week2"):
        return week2
    elif (option == "week3"):
        return week3
    elif (option == "week4"):
        return week4

def viewingProcess():
    patientView = Toplevel()
    patientView.attributes("-fullscreen", True)  # Make the window fullscreen
    patientView.title("View Schedule")

    week1 = getWeeklyPatients("week1")
    week2 = getWeeklyPatients("week2")
    week3 = getWeeklyPatients("week3")
    week4 = getWeeklyPatients("week4")

    def create_scrollable_frame(parent):
        frame = Frame(parent, width=200, height=100, borderwidth=2, relief="solid")
        frame.pack(side=TOP, fill=BOTH, expand=True, padx=5, pady=5)
        canvas = Canvas(frame)
        canvas.pack(side=LEFT, fill="both", expand=True)
        scrollbar = ttk.Scrollbar(frame, orient="vertical", command=canvas.yview)
        scrollbar.pack(side=RIGHT, fill="y")
        canvas.configure(yscrollcommand=scrollbar.set)
        inner_frame = Frame(canvas)
        canvas.create_window((0, 0), window=inner_frame, anchor="nw")
        inner_frame.bind("<Configure>", lambda e: canvas.configure(scrollregion=canvas.bbox("all")))
        return inner_frame

    # Display week1 patients
    firstWeek = create_scrollable_frame(patientView)

    firstLabel = Label(firstWeek, text="Week 1")
    firstLabel.pack()

    for i in week1:
        label = Label(firstWeek, text=i)
        label.pack()

    # Display week2 patients
    secondWeek = create_scrollable_frame(patientView)

    secondLabel = Label(secondWeek, text="Week 2")
    secondLabel.pack()

    for i in week2:
        label = Label(secondWeek, text=i)
        label.pack()

    # Display week3 patients
    thirdWeek = create_scrollable_frame(patientView)

    thirdLabel = Label(thirdWeek, text="Week 3")
    thirdLabel.pack()

    for i in week3:
        label = Label(thirdWeek, text=i)
        label.pack()

    # Display week4 patients
    fourthWeek = create_scrollable_frame(patientView)

    fourthLabel = Label(fourthWeek, text="Week 4")
    fourthLabel.pack()

    for i in week4:
        label = Label(fourthWeek, text=i)
        label.pack()

    patientView.mainloop()
