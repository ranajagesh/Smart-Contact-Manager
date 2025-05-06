console.log("script loaded")


// change theme work started

let currentTheme = getTheme();
// console.log(currentTheme);
// initial theme -->

document.addEventListener('DOMContentLoaded', () => {
    changeTheme(); 
});

// TODO:
function changeTheme() {
    //  console.log(currentTheme); 
    // set to web page
    changePageTheme(currentTheme,currentTheme);
    // set the listener to change the theme button
    const changeThemeButton = document.querySelector("#theme_change_button");   
    
    changeThemeButton.addEventListener("click",(event) =>{
        let oldTheme = currentTheme;
        // print the theme
        console.log("change theme button clicked");
        if(currentTheme === "dark") {
            // change to light
            currentTheme = "light";
        } else{
            // change to dark
            currentTheme = "dark";
        }   
        changePageTheme(currentTheme,oldTheme);   
    });
}

// set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme",theme);
}

// get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme:"light";
}

// change current page theme
function changePageTheme (theme ,oldTheme) {
    
    // to update in local storage
    setTheme(currentTheme);
    // remove the current theme
    if(oldTheme) {
        document.querySelector("html").classList.remove(oldTheme);
    }
    // set the current theme
    document.querySelector("html").classList.add(theme);
        // change the text of button
    document.querySelector('#theme_change_button').querySelector("span").textContent = 
        theme == "light" ? "dark" : "light";

}
//  end of page theme work