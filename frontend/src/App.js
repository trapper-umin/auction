import './Style/App.css';
import Header from "./Components/Auction/Header";
import Nav from "./Components/Auction/Nav";
import Content from "./Components/Auction/Content";

function App() {
  return (
    <div className="application-wrapper">
        <Header></Header>
        <Nav></Nav>
        <Content></Content>

    </div>
  );
}

export default App;
