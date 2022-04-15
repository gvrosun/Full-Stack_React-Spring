import { useState } from "react";
import "./AddServer.css";

function AddServer(props) {
  const [name, setName] = useState("");
  const [language, setLanguage] = useState("");
  const [framework, setFramework] = useState("");
  const submitHandler = (event) => {
    event.preventDefault();
    fetch(
      `http://localhost:8080/addNewServer?name=${name}&language=${language}&framework=${framework}`
    )
      .then((response) => response.json())
      .then((data) => props.onNewServer(data));
  };
  return (
    <div>
      <form onSubmit={submitHandler}>
        <label for="sname">Server Name</label>
        <input
          type="text"
          id="sname"
          name="serverName"
          onChange={(event) => {
            setName(event.target.value);
          }}
          placeholder="Enter server name..."
          required
        />
        <label for="lang">Server Language</label>
        <input
          type="text"
          id="lang"
          name="language"
          onChange={(event) => {
            setLanguage(event.target.value);
          }}
          placeholder="Enter server language..."
          required
        />
        <label for="frame">Server Framework</label>
        <input
          type="text"
          id="frame"
          name="framework"
          onChange={(event) => {
            setFramework(event.target.value);
          }}
          placeholder="Enter server framework..."
          required
        />
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}

export default AddServer;
