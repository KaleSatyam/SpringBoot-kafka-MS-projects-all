console.log("me creating project")

const togglesidebar=()=>{
    if($('.sidebar').is(":visible"))
    {
        $('.sidebar').css("display","none");
        $('.content').css("margin-left","0%")
    }else{

        $('.sidebar').css("display","block");
        $('.content').css("margin-left","20%")

    }
    
    
}

/*
// add contact form  validation

const form=document.getElementById('form');
const Name=document.getElementById('Name');
const Nickname=document.getElementById('Nickname');
const Work=document.getElementById('Work');
const Email=document.getElementById('Email');
const phonenumber=document.getElementById('phonenumber');





form.addEventListener('submit',(e)=>{
    e.preventDefault();
    validate();
});


const validate=()=>{
	const nameval=Name.value.trim();
    const nicknameval=Nickname.value.trim();
    const workval=Work.value.trim();
    const emailval=Email.value.trim();
    const phoneval=phonenumber.value.trim(); 
    
    if(nameval=='')
    {
        //show error
        seterror(Name,"Name cannot be null");
    } 
    else if(nameval.length<=5)  
    {
    seterror(Name,"Name shouldn't be less than 5 characters");
    }
    else{
        //show success
        setsuccess(Name);
    }
    
    //name field
    if(nameval=='')
    {
        //show error
        seterror(Name,"Name cannot be null");
    } 
    else if(nameval.length<=5)  
    {
    seterror(Name,"Name shouldn't be less than 5 characters");
    }
    else{
        //show success
        setsuccess(Name);
    }

    //nickname field
    if(nicknameval=='')
    {
        //show error
        seterror(Nickname,"NickName cannot be null");
    } else{
        //show success
        setsuccess(Nickname);
    }


    //work field
    if(workval=='')
    {
        //show error
        seterror(Work,"Work cannot be null");
    } else{
        //show success
        setsuccess(Work);
    }


    //email field
    if(emailval=='')
    {
        //show error
        seterror(Email,"email cannot be null");
    } else{
        //show success
        setsuccess(Email);
    }


    //phone field
    if(phoneval=='')
    {
        //show error
        seterror(phonenumber,"phonenumber cannot be null");
    } else{
        //show success
        setsuccess(phonenumber);
    }
	
}



 function seterror(input ,message)
 {
    const formcontrol=input.parentElement;
    const small=formcontrol.querySelector('small');


    small.innerText=message;
    // small.className='error'
   // small.classList.add("error");
    formcontrol.className='input-group error'
 }

  function  setsuccess(input){
    const formcontrol=input.parentElement;
    const small=formcontrol.querySelector('small');

    small.innerText='';

    formcontrol.className='input-group success';
  } 
  **/
  