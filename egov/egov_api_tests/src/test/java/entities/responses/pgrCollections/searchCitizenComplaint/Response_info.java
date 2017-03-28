package entities.responses.pgrCollections.searchCitizenComplaint;

public class Response_info {
    private String api_id;
    private String ver;
    private String res_msg_id;
    private String msg_id;
    private String ts;
    private String status;

    public String getApi_id() {
        return this.api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getRes_msg_id() {
        return this.res_msg_id;
    }

    public void setRes_msg_id(String res_msg_id) {
        this.res_msg_id = res_msg_id;
    }

    public String getMsg_id() {
        return this.msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getTs() {
        return this.ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
