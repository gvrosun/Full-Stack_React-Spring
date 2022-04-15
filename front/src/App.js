import DisplayServers from "./components/DisplayServers";
import AddServer from "./components/AddServer";
import HomePage from "./components/HomePage";
import { useState } from "react";

function App() {
  const [servers, addServers] = useState({});
  const serverHandler = (server) => {
    console.log(servers);
    console.log(server);
    addServers({ ...servers, server });
    console.log(servers);
  };
  return (
    <div>
      {/* <DisplayServers onFetchServers={serverHandler} />
      <AddServer onNewServer={serverHandler} /> */}
      <HomePage />
    </div>
  );
}

export default App;
