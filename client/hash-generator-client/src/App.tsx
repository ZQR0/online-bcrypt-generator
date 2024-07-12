import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import { emit, env } from 'process';
import { error } from 'console';


const hashGeneratorEndpoint = "http://localhost:7777/api/v1/get-hash";
const verifyHashEndpoint = "http://localhost:7777/api/v1/verify-hash";


function changeResultText(hash: string, error: string): void {
  let obj = document.getElementById('resultText');

  if (hash != "" && obj != null) {
    obj.innerHTML = hash;
  }

  if (error != "" && obj != null) {
    obj.innerHTML = error;
  }

}

function changeVerifyResultText(result: string, error: string): void {
  let resultParagraph = document.getElementById('resultVerify');

  if (result != '' && resultParagraph != null) {
    resultParagraph.innerHTML = result;
  }

  if (error != '' && resultParagraph != null) {
    resultParagraph.innerHTML = error;
  }

}

function sendDataToGenerate(inputData: string, costFactorNum: number): void {
  if (inputData == null || inputData == '') {
    console.log("Input data is null or empty");
  }

  if (costFactorNum == null || costFactorNum == 0) {
    console.log("costFactorNum is null or empty");
  }

  fetch(hashGeneratorEndpoint, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify({data: inputData, costFactor: String(costFactorNum)})
  })
  .then((response) => response.json())
  .then(respData => {
    console.log(respData.hash)
    changeResultText(respData.hash, respData.error)
  })
  .catch((error) => console.log(error));
}


function sendDataToVerify(plainText: string, hashString: string) {
  if (plainText == null || plainText == '') {
    console.log("Plain text is empty or null");
  }

  if (hashString == null || hashString == '') {
    console.log("Hash string is empty or null");
  }

  fetch(verifyHashEndpoint, {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({plainText: plainText, hash: hashString})
  })
  .then((resp) => resp.json())
  .then((resp) => changeVerifyResultText(resp.result, resp.error))
  .catch((error) => console.log(error))
}


function App() {

  const [data, setData] = useState('');
  const [costFactor, setCostFactor] = useState(0);

  const [plainText, setPlainText] = useState('');
  const [verifyHash, setVerifyHash] = useState('');

  useEffect(() => {
    console.log('data: ' + data)
    console.log('costFactor: ' + costFactor)
    console.log('Plain text: ' + plainText)
    console.log('Verify hash: ' + verifyHash)
  });

  return (
    <div className='container'>

      <p className='titleText'>Online BCRYPT Generator</p>

      <div className='formContainer'>

        <input className='inputData' placeholder='Type your text' id='inputData' onChange={(e) => setData(e.target.value)}></input>

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

        <button className='generateButton' onClick={(e) => sendDataToGenerate(data, costFactor)}>Generate</button>

        <div id='outputContainer' className='outputContainer'>
          <p className='resultText' id='resultText'>Result</p>
        </div>

      </div>

      <p className='verifierTitle'>Verify your hash</p>
      <div className='verifyHashContainer'>
        <input className='plainTextInputField' placeholder='Plain text' onChange={(e) => setPlainText(e.target.value)}></input>
        <input className='hashInputField' placeholder='Hash' onChange={(e) => setVerifyHash(e.target.value)}></input>
        <button className='verifyHashButton' onClick={(e) => sendDataToVerify(plainText, verifyHash)}>Verify</button>
        <p className='resultVerify' id='resultVerify'></p>
      </div>
    </div>
  );
}

export default App;
