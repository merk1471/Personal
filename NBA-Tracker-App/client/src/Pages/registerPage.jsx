export const RegisterPage = () =>{
    

    const fetchData = async () =>{
        try{
            const userN = document.getElementById('usr').value;
        const passwrd = document.getElementById('pswrd').value;
        const dataToSend = {
        username : userN,
        password : passwrd,
         }
            const response = await fetch('http://localhost:3000/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify(dataToSend),
            });
            
            if (!response.ok){
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log("Data from the server: " + data);
        }
        catch (error) {
            console.error('Error fetching data: ' + error.message);
        }
    }

    const handleSubmit = (event) =>{
        window.location.href = '/login';
        event.preventDefault();
        fetchData();
        console.log('works');
    };

    return(
        <form onSubmit={handleSubmit}>
            <input className="typingBox" type="text" placeholder="username"/> <br />
            <input className="typingBox" type="text" placeholder="password"/> <br />
            <button className="submitBtn">Register</button> 
        </form>
    )
}