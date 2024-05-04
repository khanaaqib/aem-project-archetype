$(document).ready(function(){
    window.onload = async () => {
       await configureClient();
        updateUI();
        const isAuthenticated = await auth0Client.isAuthenticated();

  if (isAuthenticated) {
    // show the gated content
    return;
  }

  // NEW - check for the code and state parameters
  const query = window.location.search;
  if (query.includes("code=") && query.includes("state=")) {

    // Process the login state
    await auth0Client.handleRedirectCallback();
    
    updateUI();

    // Use replaceState to redirect the user away and remove the querystring parameters
    window.history.replaceState({}, "",   "?");
  }
    }
});

let auth0Client = null;
   const configureClient = async () => {
      auth0Client = await auth0.createAuth0Client({
        domain: "dev-312w761fcyq2qxpr.us.auth0.com",
        clientId: "ehqrzy7mq2vVRXyLDN6msny4c5Gza68R"
      });
};

const updateUI = async () => {
  const isAuthenticated = await auth0Client.isAuthenticated();

  document.getElementById("btn-logout").disabled = !isAuthenticated;
  document.getElementById("btn-login").disabled = isAuthenticated;
};

const login = async () => {
  await auth0Client.loginWithRedirect({
    authorizationParams: {
      redirect_uri: window.location.href
    }
  });
};



const logout = () => {
  auth0Client.logout({
    logoutParams: {
      returnTo: window.location.href
    }
  });
};