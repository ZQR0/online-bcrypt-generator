import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';


function changeResultText(newText: string) {
  let obj = document.getElementById('resultText');
  if (obj != null) {
    obj.innerHTML = newText;
  }
}

function sendData(inputData: string, costFactorNum: number) {
  if (inputData == null || inputData == '') {
    console.log("Input data is null");
  }

  if (costFactorNum == null || costFactorNum == 0) {
    console.log("costFactorNum is null");
  }

  fetch('http://localhost:7777/api/v1/get-hash', {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify({data: inputData, costFactor: String(costFactorNum)})
  })
  .then((response) => response.json())
  .then(respData => {
    console.log(respData.hash)
    changeResultText(respData.hash)
  })
  .catch((error) => console.log(error));
}


function App() {

  const [data, setData] = useState('');
  const [costFactor, setCostFactor] = useState(0);
  const [outputText, setOutputText] = useState('');

  useEffect(() => {
    console.log('data: ' + data)
    console.log('costFactor' + costFactor)
  });

  return (
    <div className='container'>

      <p className='titleText'>Online BCRYPT Generator</p>

      <div className='formContainer'>

        <input className='inputData' placeholder='Введите ваш текст' id='inputData' onChange={(e) => setData(e.target.value)}></input>

        <select id='costFactorSelect' className='costFactorSelect' onChange={(e) => setCostFactor(Number(e.target.value))}>
          <option value="0">0</option>
          <option value="6">6</option>
          <option value="8">8</option>
          <option value="10">10</option>
          <option value="12">12</option>
          <option value="14">14</option>
          <option value="16">16</option>
          <option value="18">18</option>
          <option value="20">20</option>
        </select>

        <button className='generateButton' onClick={(e) => sendData(data, costFactor)}>Сгенерировать</button>

        <div id='outputContainer' className='outputContainer'>
          <p className='resultText' id='resultText'>Результат</p>
        </div>

      </div>
    </div>
  );
}

export default App;
