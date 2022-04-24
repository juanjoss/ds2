const updateDisplay = (value = "") => {
    if(document.querySelector('.calculator-screen').value === "invalid") {
        console.log("invalid");
        resetDisplay();
        resetUrlExpression();
    }
    
    if(value === "+") {
        document.querySelector('.calculator-screen').value += value;
        document.getElementById("urlExpression").href += "%2B";
        return;
    }
    
    document.querySelector('.calculator-screen').value += value;
    document.getElementById("urlExpression").href += value;
};

const resetDisplay = () => document.querySelector('.calculator-screen').value = "";

const resetUrlExpression = () => document.getElementById("urlExpression").href = "/client/calculator.jsp?expression=";

const keys = document.querySelector('.calculator-keys');
keys.addEventListener('click', (event) => {
    const value = event.target.value;
    
    if(value === "AC") {
        resetDisplay();
        resetUrlExpression();
        return;
    }
    
    updateDisplay(value);
});