import AboutPerson from "./AboutPerson";
import {creators} from "../../Data/creators";

const Content=()=>{
    return(
        <div>
            <AboutPerson name={creators[0].name} about={creators[0].about} src={creators[0].link}></AboutPerson>
            <AboutPerson name={creators[1].name} about={creators[1].about} src={creators[1].link}></AboutPerson>
            <AboutPerson></AboutPerson>
        </div>
    );
}

export default Content;