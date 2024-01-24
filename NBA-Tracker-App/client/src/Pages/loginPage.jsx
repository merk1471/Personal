export const LoginPage = () =>{


    const fetchData = async () =>{
        try{
            const userN = document.getElementById('usr').value;
        const passwrd = document.getElementById('pswrd').value;
        const dataToSend = {
        name : userN,
        password : passwrd,
         }
            const response = await fetch('http://localhost:3000/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',

                },
                body: JSON.stringify(dataToSend),
            });
            
            if (!response.ok){
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            window.location.href = '/userPage';
            const data = await response.json();
            console.log("Data from the server: " + data.user.name + " " + data.user.password);
        }   
        catch (error) {
            console.error('Error fetching data: ' + error.message);
        }
    }

    const handleSubmit = (event) =>{
        event.preventDefault();
        fetchData();
        console.log('works');
    };

    return(
        <>
            <form onSubmit={handleSubmit}>
                <input id='usr' className="typingBox" type="text" placeholder="username" /> <br />
                <input id='pswrd' className="typingBox" type="text" placeholder="password" /> <br />
                <button type='submit' className="submitBtn">Login</button>
            </form>
        </>
    );
};