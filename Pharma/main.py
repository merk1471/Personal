from tkinter import *
from tkinter import ttk
from managePatient import *

#Create the main window
window = Tk()
window.title("Patient Manager")
window.geometry("800x800")

#Create a frame for the buttons
optionsFrame = ttk.Frame(window)
optionsFrame.pack()
    

#Management buttons
removeButton = Button(optionsFrame, text="Remove a Patient", command=removeProcess)
Addbutton = Button(optionsFrame, text="Add a Patient", command=addingProcess)
viewButton = Button(optionsFrame, text="View Schedule", command=viewingProcess)

removeButton.pack(side=LEFT)
Addbutton.pack(side=LEFT)
viewButton.pack(side=LEFT)

deliveryInfoPage = Frame(window)
deliveryInfoPage.pack()
#Display weekly deliveries
weeklyDeliveries = Label(deliveryInfoPage, text="Welcome! \n Here are your weekly deliveries.")
weeklyDeliveries.pack()

def restartDisplay():
    weeklyPatients = getWeeklyPatients("current")

    if not (weeklyPatients):
        label = Label(deliveryInfoPage, text="No patients due this week")
        label.pack()

    for i in (weeklyPatients):
        print(i)
        label = Label(deliveryInfoPage, text=i)
        label.pack()

restartDisplay()
#update all user dates
updateUsers()
window.mainloop()