class Message {
    constructor(type, body) {
        this.type = type;
        this.body = body;
        this.created_at = new Date();
        this.created_by = sessionStorage.getItem('username');
    }
}

export default Message;