package Models.Gameplay;

public class Chat {
    private String username = "";
    private String message = "";

    public Chat() {
    }

    public Chat(String msg){
        message = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String displayChat(){
        if(username.equals("")) { return message; }
        return username + ": "+ message;
    }




}
