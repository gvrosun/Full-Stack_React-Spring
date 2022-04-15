import { Component } from "react";
import "./DisplayServers.css";

class DisplayServers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      servers: [],
    };
  }
  componentDidMount() {
    fetch("http://localhost:8080/getAllServers")
      .then((response) => response.json())
      .then((data) =>
        this.setState({ servers: data.map((item) => JSON.parse(item)) })
      );
    this.props.onFetchServers(this.state.servers);
  }
  render() {
    return (
      <div>
        <h1 style={{ textAlign: "center" }}>Servers</h1>
        <table id="customers">
          <tr>
            <th>Id</th>
            <th>Server Name</th>
            <th>Language</th>
            <th>Framework</th>
          </tr>
          {this.state.servers.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.language}</td>
              <td>{item.framework}</td>
            </tr>
          ))}
        </table>
      </div>
    );
  }
}

export default DisplayServers;
