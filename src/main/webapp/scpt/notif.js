     function verifyClient() {
var cin = $("#cin").val();
var client = listClient.find(obj => obj.cin === cin);


if (client) {
 
 $("#clientEx").show();


 $("#nomclient").val(client.nomComplet);
 $("#tele").val(client.telephone);
 $("#adress").val(client.adresse);
} 
else {

 $("#clientEx").show();
 $("#nomclient").val("");
 $("#adress").val("");
 $("#tele").val("");
}

}
    
    
    
    
    
      function toggleNotifications() {
            var notifications = document.getElementById("notificationMessages");
            notifications.style.display = (notifications.style.display === "none") ? "block" : "none";
        }

        function markAsRead(notificationId) {
            // Check if the notification has already been marked as read
        var shortMessage = document.getElementById(notificationId).getElementsByClassName('short-message')[0];
        var fullMessage = document.getElementById(notificationId).getElementsByClassName('full-message')[0];

        if (fullMessage.style.display === 'none') {
            shortMessage.style.display = 'none';
            fullMessage.style.display = 'block';
        } else {
            shortMessage.style.display = 'block';
            fullMessage.style.display = 'none';
        }
            var notificationCard = document.getElementById(notificationId);
            if (notificationCard.classList.contains("read-notification")) {
                return; // Do nothing if already read
            }

            MODIFYDataBase(notificationId);
      

            

            // Update the notification count (you may want to fetch it from the server)
            var notificationCount = parseInt(document.getElementById("notificationCount").innerHTML);
            if (notificationCount > 0) {
                notificationCount--;
                document.getElementById("notificationCount").innerHTML = notificationCount;

                // Hide the badge if there are no more notifications
                document.getElementById("notificationCount").style.display = (notificationCount > 0) ? "block" : "none";
            }

            // Toggle background color to indicate the notification has been read
            notificationCard.classList.add("read-notification");
        }
      function MODIFYDataBase(notificationId) {
    var xhr = new XMLHttpRequest();
    alert("hi" + notificationId);
    
    xhr.open("POST", "/TPl/updateit.jsp?notificationId=" + notificationId, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            console.log("Request successful");
            console.log("Response: " + xhr.responseText);
        } else {
            console.error("Request failed with status: " + xhr.status);
        }
    }
};

    // You need to add the following line to send the request
    xhr.send();
}

        
     