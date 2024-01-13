let inputString = "";

function buttonClicked(buttonId)
{
    switch(buttonId)
    {
        case 'btnOne':
            inputString += 1;
            break;
        case "btnTwo":
            inputString += 2;
            break;
        case "btnThree":
            inputString += 3;
            break;
        case "btnFour":
            inputString += 4;
            break;
        case "btnFive":
            inputString += 5;
            break;
        case "btnSix":
            inputString += 6;
            break;
        case "btnSeven":
            inputString += 7;
            break;
        case "btnEight":
            inputString += 8;
            break;
        case "btnNine":
            inputString += 9;
            break;
        case "btnZero":
            inputString += 0;
            break;
        case "btnClear":
            inputString = "";
            break;

    }
    document.getElementById("answerScreen").textContent = inputString;
}

function performOperation(operator) {
    let temp = inputString;
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;

    let operation = {
        operator: operator,
        operand: Number(temp)
    };

    function handleEqualsClick() {
        switch (operation.operator) {
            case "/":
                console.log("hi");
                inputString = operation.operand / Number(inputString);
                break;
            case "*":
                inputString = operation.operand * Number(inputString);
                break;
            case "-":
                inputString = operation.operand - Number(inputString);
                break;
            case "+":
                inputString = operation.operand + Number(inputString);
                break;
        }

        document.getElementById("answerScreen").textContent = inputString;

        document.getElementById("equals").removeEventListener("click", handleEqualsClick);
    }

    document.getElementById("equals").addEventListener("click", handleEqualsClick);
}
