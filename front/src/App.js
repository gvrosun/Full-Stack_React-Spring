import { useState } from "react";

function App() {
  const [name, setName] = useState([]);
  fetch("http://localhost:8080/getAllServers")
    .then((response) => response.json())
    .then((data) => setName(data.map((item) => JSON.parse(item))));
  return (
    <div>
      {name.map((item) => (
        <p key={item.id}> {item.name} </p>
      ))}
    </div>
  );
}

export default App;
