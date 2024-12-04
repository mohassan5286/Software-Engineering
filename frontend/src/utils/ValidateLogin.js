function validation(values){
    let error =  {};
    const email_pattern =  /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const password_pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if(values.email=== ""){
        error.email="email should not be empty"
    }else if(!email_pattern.test(values.email)){
        error.email="Email dont match"
    }else{
        error.email=""
    }
    if(values.password=== ""){
        error.password="password should not be empty"
    }else if(!password_pattern.test(values.password)){
        error.password="Password dont match"
    }else{
        error.password=""
    }
    console.log(error)
    return error;
    

}
export default validation;