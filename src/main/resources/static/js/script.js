console.log("script loading");


//change theme work
let currentTheme=getTheme();

//initially ->
document.addEventListener('DOMContentLoaded',()=>{
    changeTheme();
})

//TODO
function changeTheme(){
    //set to web page
    changePageTheme(currentTheme,currentTheme);
    //set the listener to change the theme based on click on button
    const changeThemeButton=document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click",(event)=>{
        let oldTheme=currentTheme;
        console.log("change theme button clicked")
        if(currentTheme==='dark'){
            //theme to light
            currentTheme='light';
        }
        else{
            //theme to dark
            currentTheme='dark';
        }
        changePageTheme(currentTheme,oldTheme);
    });
}

//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}

//get theme from local storage
function getTheme(){
    let theme=localStorage.getItem("theme");
    return theme? theme:"light";
}

//change current page theme
function changePageTheme(theme,oldTheme){
    //update in localstorage
    setTheme(theme);
    //remove currentTheme
    document.querySelector("html").classList.remove(oldTheme);

    //add the changed theme
    document.querySelector("html").classList.add(theme);

    document.querySelector("#theme_change_button").querySelector("span").textContent=
    theme=="light"?"Dark":"Light";
}

//end of change theme working

