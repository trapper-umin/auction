import {useState} from "react";

const AboutPerson=(props)=>{
    const [details,setDetails]=useState(false)
    const btnStyle=details ? "content-button-on" : "content-button-off";

    return(
        <div className="content">
            <img className="content-img" src={props.src}></img>
            <div>
                <h1 className="content-name">{props.name}</h1>
                {details && <h4 className="content-about">{props.about}</h4>}
                <button className={btnStyle}
                onClick={()=>setDetails(prev=>!prev)}
                >{!details ? "Show description" : "Hide description"}</button>
            </div>
        </div>
    );
}

export default AboutPerson;