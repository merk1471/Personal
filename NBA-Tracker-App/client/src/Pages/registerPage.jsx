export const RegisterPage = () => {
    const fetchData = async () => {
      try {
        const userN = document.getElementById('usr').value;
        const passwrd = document.getElementById('pswrd').value;
        const dataToSend = {
          name: userN,
          password: passwrd,
        };
  
        const response = await fetch('http://localhost:3000/users/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(dataToSend),
        });
  
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        

        const data = await response.json();
        console.log('Data from the server: ', data);
      } catch (error) {
        console.error('Error fetching data: ' + error.message);
      }
    };
  
    const handleSubmit = (event) => {
      window.location.href = '/login';
      event.preventDefault();
      fetchData();
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <input id="usr" className="typingBox" type="text" placeholder="username" /> <br />
        <input id="pswrd" className="typingBox" type="text" placeholder="password" /> <br />
        <button type="submit" className="submitBtn">
          Register
        </button>
      </form>
    );
  };
  