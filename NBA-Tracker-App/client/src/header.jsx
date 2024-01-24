export default function Header (){
    return(
    <div className="header">
        <a href="/"><h1>TrackerBuddy</h1></a>

        <a href="./login"> <button id='login' className='btn'>Login</button></a>

        <a href="./register"> <button id='register' className='btn'>Register</button></a>
       
        
    </div>
    )
}