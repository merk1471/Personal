let inputString = "";

document.getElementById("1").onclick = function()
{
    inputString += 1;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("2").onclick = function()
{
    inputString += 2;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("3").onclick = function()
{
    inputString += 3;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("4").onclick = function()
{
    inputString += 4;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("5").onclick = function()
{
    inputString += 5;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("6").onclick = function()
{
    inputString += 6;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("7").onclick = function()
{
    inputString += 7;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("8").onclick = function()
{
    inputString += 8;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("9").onclick = function()
{
    inputString += 9;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("zero").onclick = function()
{
    inputString += 0;
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("clear").onclick = function()
{
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;
}


document.getElementById("division").onclick = function()
{
    let temp = inputString;
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;

    document.getElementById("equals").addEventListener("click", function() {
        inputString = Number(temp) / Number(inputString)
        document.getElementById("answerScreen").innerHTML = inputString;
    });
}

document.getElementById("multiplication").onclick = function()
{
    let temp = inputString;
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;

    document.getElementById("equals").addEventListener("click", function() {
        inputString = Number(temp) * Number(inputString)
        document.getElementById("answerScreen").innerHTML = inputString;
    });
}

document.getElementById("subtraction").onclick = function()
{
    let temp = inputString;
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;

    document.getElementById("equals").addEventListener("click", function() {
        inputString = Number(temp) - Number(inputString)
        document.getElementById("answerScreen").innerHTML = inputString;
    });
}
document.getElementById("dot").onclick = function()
{
    inputString += " . ";
    document.getElementById("answerScreen").innerHTML = inputString;
}

document.getElementById("addition").onclick = function()
{
    let temp = inputString;
    inputString = "";
    document.getElementById("answerScreen").innerHTML = inputString;

    document.getElementById("equals").addEventListener("click", function() {
        inputString = Number(temp) + Number(inputString)
        document.getElementById("answerScreen").innerHTML = inputString;
    });
    
}

