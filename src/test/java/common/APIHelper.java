package common;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import testdataobjects.UserAuthenticate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class APIHelper {

    public String getLoginUserTimeZone(String wsendpoint, String username, String password){

        String timeZone = "";
        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        Response response = request.post("/api/User/UserAuthenticate");

        int statusCode = response.getStatusCode();

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            timeZone = jsonPathEvaluator.get("User.TimeZone");
        }
        return timeZone;
       }

    public UserAuthenticate getLoginUserInfo(String wsendpoint, String username){

        UserAuthenticate userAuthenticate = new UserAuthenticate();
        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        //requestParams.put("password", password);
        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        Response response = request.post("/api/User/UserAuthenticate");

        int statusCode = response.getStatusCode();
        System.out.println("UserAuthenticate statusCode: " + statusCode);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            userAuthenticate.setFullName(jsonPathEvaluator.get("User.FullName"));
            userAuthenticate.setEmployeeID(jsonPathEvaluator.get("User.EmployeeID"));
            userAuthenticate.setUserLevel(jsonPathEvaluator.get("User.UserLevel"));
            userAuthenticate.setEmployeeLevel(jsonPathEvaluator.get("User.EmployeeLevel"));
            userAuthenticate.setCampaignName(jsonPathEvaluator.get("User.Campaign"));
            userAuthenticate.setTimeZone(jsonPathEvaluator.get("User.TimeZone"));
            userAuthenticate.setTimeZoneIANA(jsonPathEvaluator.get("User.TimeZoneIANA"));
            userAuthenticate.setTimeZoneOffset(jsonPathEvaluator.get("User.TimeZoneOffset"));
            userAuthenticate.setUserIdentity(jsonPathEvaluator.get("User.UserIdentity"));
            userAuthenticate.setAuthenticationToken(jsonPathEvaluator.get("AuthenticationToken"));
            List<String> supervisors = jsonPathEvaluator.getList("User.Supervisors");
            for(int i=1; i<=supervisors.size(); i++){
                if(i == 1){ userAuthenticate.setSupervisor1(jsonPathEvaluator.get("User.Supervisors[" + i + "].EmployeeNo"));}
                if(i == 2){ userAuthenticate.setSupervisor2(jsonPathEvaluator.get("User.Supervisors[" + i + "].EmployeeNo"));}
                if(i == 3){ userAuthenticate.setSupervisor3(jsonPathEvaluator.get("User.Supervisors[" + i + "].EmployeeNo"));}
                if(i == 4){ userAuthenticate.setSupervisor4(jsonPathEvaluator.get("User.Supervisors[" + i + "].EmployeeNo"));}
            }

        }

        return userAuthenticate;
    }

    public int createACompleteActivityLogForAUser(String wsendpoint, UserAuthenticate userinfo){

        String startTime = null;
        String endTime = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("employeeID", userinfo.getEmployeeID());
        requestParams.put("approver", userinfo.getSupervisor1());
        requestParams.put("approverSub", userinfo.getSupervisor2());
        requestParams.put("ticketStatus", JSONObject.NULL);

        ArrayList<JSONObject> reqArray= new ArrayList<JSONObject>();
        JSONObject json= new JSONObject();

        try {
            json.put("ID", JSONObject.NULL);
            json.put("TicketID", JSONObject.NULL);
            json.put("ActionReasonID", 3);
            json.put("ActionType", "Insert");
            json.put("ActivityLogID", JSONObject.NULL);
            json.put("EmployeeID", userinfo.getEmployeeID());
            json.put("ActivityTypeID", 3);

            startTime = TimeCalendar
                    .getStartTimeValueForTodayAndNineHoursFromNow(userinfo.getTimeZoneIANA());
            endTime = TimeCalendar
                    .getTimeValueGivenTimeAndMinusOrAddHours(startTime, 1, "add",
                            "Minute", "yyyy-MM-dd HH:mm:ss");

            System.out.println("startTime: [" + startTime + "] endTime: [" + endTime + "]");
            json.put("StartTime", startTime);
            json.put("EndTime", endTime);
            json.put("Campaign", userinfo.getCampaignName());
            json.put("IS1", userinfo.getSupervisor1());
            json.put("IS2", userinfo.getSupervisor2());
            json.put("IS3",userinfo.getSupervisor3());
            json.put("IS4",userinfo.getSupervisor4());
            json.put("TimeZone", userinfo.getTimeZone());
            json.put("TimeZoneOffset", userinfo.getTimeZoneOffset());
            json.put("Remark", JSONObject.NULL);
            json.put("CreatedBy", userinfo.getUserIdentity());
            json.put("CreatedDate", JSONObject.NULL);
            json.put("UpdatedBy", userinfo.getUserIdentity());
            json.put("UpdatedDate", JSONObject.NULL);
            json.put("IpAddress", "192.168.254.110");
            json.put("MachineName", JSONObject.NULL);

            reqArray.add(json);
            json= new JSONObject();
            json.put("ID", JSONObject.NULL);
            json.put("TicketID", JSONObject.NULL);
            json.put("ActionReasonID", 6);
            json.put("ActionType", "Insert");
            json.put("ActivityLogID", JSONObject.NULL);
            json.put("EmployeeID", userinfo.getEmployeeID());
            json.put("ActivityTypeID", 5);

            startTime = endTime;
            endTime = TimeCalendar
                    .getTimeValueGivenTimeAndMinusOrAddHours(startTime, 8, "add",
                            "Hour", "yyyy-MM-dd HH:mm:ss");
            System.out.println("startTime: [" + startTime + "] endTime: [" + endTime + "]");

            json.put("StartTime", startTime);
            json.put("EndTime", endTime);
            json.put("Campaign", userinfo.getCampaignName());
            json.put("IS1", userinfo.getSupervisor1());
            json.put("IS2", userinfo.getSupervisor2());
            json.put("IS3",userinfo.getSupervisor3());
            json.put("IS4",userinfo.getSupervisor4());
            json.put("TimeZone", userinfo.getTimeZone());
            json.put("TimeZoneOffset", userinfo.getTimeZoneOffset());
            json.put("Remark", JSONObject.NULL);
            json.put("CreatedBy", userinfo.getUserIdentity());
            json.put("CreatedDate", JSONObject.NULL);
            json.put("UpdatedBy", userinfo.getUserIdentity());
            json.put("UpdatedDate", JSONObject.NULL);
            json.put("IpAddress", JSONObject.NULL);
            json.put("MachineName", JSONObject.NULL);
            reqArray.add(json);

            json= new JSONObject();
            json.put("ID", JSONObject.NULL);
            json.put("TicketID", JSONObject.NULL);
            json.put("ActionReasonID", 6);
            json.put("ActionType", "Insert");
            json.put("ActivityLogID", JSONObject.NULL);
            json.put("EmployeeID", userinfo.getEmployeeID());
            json.put("ActivityTypeID", 4);

            startTime = endTime;
            endTime = startTime;
            System.out.println("startTime: [" + startTime + "] endTime: [" + endTime + "]");

            json.put("StartTime", startTime);
            json.put("EndTime", endTime);
            json.put("Campaign", userinfo.getCampaignName());
            json.put("IS1", userinfo.getSupervisor1());
            json.put("IS2", userinfo.getSupervisor2());
            json.put("IS3",userinfo.getSupervisor3());
            json.put("IS4",userinfo.getSupervisor4());
            json.put("TimeZone", userinfo.getTimeZone());
            json.put("CreatedBy", userinfo.getUserIdentity());
            json.put("CreatedDate", JSONObject.NULL);
            json.put("UpdatedBy", userinfo.getUserIdentity());
            json.put("UpdatedDate", JSONObject.NULL);
            json.put("IpAddress", JSONObject.NULL);
            json.put("MachineName", JSONObject.NULL);
            reqArray.add(json);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        String printjsonarray= reqArray.toString();
        requestParams.put("ticketActivityLogs", printjsonarray);
        requestParams.put("createdBy", userinfo.getEmployeeID());

        //System.out.println("requestParams: [" + requestParams + "]");
        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + userinfo.getAuthenticationToken());

        Response response = request.post("/api/Ticket/TicketSubmit");

        int statusCode = response.getStatusCode();
        System.out.println("TicketSubmit: " + statusCode);

        return statusCode;

    }

    public Integer selectAllActivityForApprovalGivenEmployeeNo(String wsendpoint, String employeeNo, String token){

        Integer ticketId = 0;
        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("draw", 1);

        ArrayList<JSONObject> reqArray= new ArrayList<JSONObject>();
        JSONObject json= new JSONObject();
        json = createColumnJSONObject(JSONObject.NULL, "", false, false, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("Content", "", true, false, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ID", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeCampaign", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeLob", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ApproverName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ApproverSubName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("TicketStatus", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("CreatedDate", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("UpdatedDate", "", true, true, "", false);
        reqArray.add(json);
        String printjsonarray= reqArray.toString();
        requestParams.put("columns", reqArray);

        ArrayList<JSONObject> orderReqArray= new ArrayList<JSONObject>();
        JSONObject order = new JSONObject();
        order.put("column", 9);
        order.put("dir", "desc");
        orderReqArray.add(order);
        requestParams.put("order", orderReqArray.toString());

        requestParams.put("start", 0);
        requestParams.put("length", 10);

        JSONObject search = new JSONObject();
        search.put("value", "");
        search.put("regex", false);
        requestParams.put("search", search);

        requestParams.put("filterType", "For Supervisor Approval");
        requestParams.put("employeeID", employeeNo);
        requestParams.put("ticketID", JSONObject.NULL);
        requestParams.put("searchColumn", JSONObject.NULL);
        requestParams.put("searchValue", JSONObject.NULL);
        requestParams.put("showOption", JSONObject.NULL);

        System.out.println("requestParams: [" + requestParams + "]");
        request.body(requestParams.toString());

        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);

        Response response = request.post("/api/Ticket/TicketSelect");

        int statusCode = response.getStatusCode();
        System.out.println("statusCode: " + statusCode);
        if(statusCode == 200){
            JsonPath jsonPathEvaluator = response.jsonPath();
            ticketId = jsonPathEvaluator.get("data[0].ID");
        }

        System.out.println("ticketId: " + ticketId);
        return ticketId;
    }

    private JSONObject createColumnJSONObject(Object data, String name, boolean searchable, boolean orderable,
                                              String searchValue, boolean searchRegex){
        JSONObject json= new JSONObject();
        JSONObject searchJSON = new JSONObject();
        searchJSON.put("value", searchValue);
        searchJSON.put("regex", searchRegex);
        json.put("data", data);
        json.put("name", name);
        json.put("searchable", searchable);
        json.put("orderable", orderable);
        json.put("search", searchJSON);

        return json;

    }

    public void approveSubmittedStartShiftActivity(String wsendpoint, String employeeNo, String token, Integer ticketid,
                                                String approver, String approver2, String startTime, String campaign,
                                                   String timeZone, String timeZoneOffset, String userIdentity,
                                                   String createdDate){

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("ticketID", ticketid);
        requestParams.put("employeeID", employeeNo);
         requestParams.put("approver", approver);
        requestParams.put("approverSub", approver2);
        requestParams.put("ticketStatus", 1);
        requestParams.put("updatedBy", approver);

        ArrayList<JSONObject> ticketReqArray= new ArrayList<JSONObject>();
        JSONObject ticketActivityLogs = new JSONObject();
        ticketActivityLogs.put("ID", JSONObject.NULL);
        ticketActivityLogs.put("TicketID", ticketid);
        ticketActivityLogs.put("ActionType", "Insert");
        ticketActivityLogs.put("ActivityLogID", JSONObject.NULL);
        ticketActivityLogs.put("EmployeeID", employeeNo);
        ticketActivityLogs.put("ActivityTypeID", 3);
        ticketActivityLogs.put("StartTime", startTime);
        ticketActivityLogs.put("EndTime", JSONObject.NULL);
        ticketActivityLogs.put("Campaign", campaign);
        ticketActivityLogs.put("Lob", "");
        ticketActivityLogs.put("IS1", approver);
        ticketActivityLogs.put("IS2", approver2);
        ticketActivityLogs.put("IS3", 0);
        ticketActivityLogs.put("IS4", 0);
        ticketActivityLogs.put("TimeZone", timeZone);
        ticketActivityLogs.put("TimeZoneOffset", timeZoneOffset);
        ticketActivityLogs.put("Remark", "");
        ticketActivityLogs.put("CreatedBy", userIdentity);
        ticketActivityLogs.put("CreatedDate", createdDate);
        ticketActivityLogs.put("UpdatedBy", "");
        ticketActivityLogs.put("UpdatedDate", JSONObject.NULL);
        ticketActivityLogs.put("MachineName", JSONObject.NULL);

        ticketReqArray.add(ticketActivityLogs);
        requestParams.put("ticketActivityLogs", ticketReqArray.toString());

        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);
        Response response = request.post("/api/Ticket/TicketApprove");

        int statusCode = response.getStatusCode();
        System.out.println("TicketApprove statusCode: " + statusCode);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
        }
    }

    public HashMap<String, String> getActivityLogSelectForEdit(String wsendpoint, String employeeID,
                                                       String fromDate, String timeZone, String timeZoneIANA,
                                                       String toDate, String token){

        HashMap<String, String> activityLogs = new HashMap<String, String>();
        String activitytype = null;
        String startTime = null;
        String endTime = null;
        String duration = null;
        String timeZoneResponse = null;
        String allString = null;
        LocalDateTime localDateTime = null;
        ZonedDateTime zonedDateTime = null;
        String startTimeKey = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        request.param("employeeID", employeeID);
        request.param("from", fromDate);
        request.param("timeZone", timeZone);
        request.param("to", toDate);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityLog/ActivityLogSelectForEdit");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        int statusCode = response.getStatusCode();
        System.out.println("getActivityLogSelectForEdit statusCode: " + statusCode);
        ZoneId zoneId = ZoneId.of(timeZoneIANA);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++){
                activitytype = jsonPathEvaluator.get("ActivityType[" + i + "]");
                startTime = jsonPathEvaluator.get("StartTime[" + i + "]");

                localDateTime = LocalDateTime.parse(startTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                startTime = formatter1.format(zonedDateTime);
                startTimeKey = formatter3.format(zonedDateTime);

                endTime = jsonPathEvaluator.get("EndTime[" + i + "]");
                if(endTime!=null){
                    localDateTime = LocalDateTime.parse(endTime, formatter2);
                    zonedDateTime = localDateTime.atZone(zoneId);
                    endTime = formatter1.format(zonedDateTime);
                }

                duration = jsonPathEvaluator.get("Duration["+ i + "]");
                timeZoneResponse = jsonPathEvaluator.get("TimeZone["+ i + "]");

                allString = "Activity Type : " + activitytype + "Start Time : " + startTime +
                        "End Time : " + endTime + "Duration : " + duration +
                        "TimeZone : " + timeZoneResponse + "Action Type : No Changes";

                activityLogs.put(activitytype.trim() + startTimeKey.trim(), allString);
            }

         }
        return activityLogs;
    }

    public List<String> getActivityLogSelectForEditForScreenLogsCompare(String wsendpoint, String employeeID,
                                                               String fromDate, String timeZone, String timeZoneIANA,
                                                               String toDate, String token){

        List<String> activityLogs = new ArrayList<String>();
        String activitytype = null;
        String startTime = null;
        String endTime = null;
        String duration = null;
        String timeZoneResponse = null;
        String allString = null;
        LocalDateTime localDateTime = null;
        ZonedDateTime zonedDateTime = null;
        String startTimeKey = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        request.param("employeeID", employeeID);
        request.param("from", fromDate);
        request.param("timeZone", timeZone);
        request.param("to", toDate);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityLog/ActivityLogSelectForEdit");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        int statusCode = response.getStatusCode();
        System.out.println("getActivityLogSelectForEdit statusCode: " + statusCode);
        ZoneId zoneId = ZoneId.of(timeZoneIANA);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++){
                activitytype = jsonPathEvaluator.get("ActivityType[" + i + "]");
                startTime = jsonPathEvaluator.get("StartTime[" + i + "]");

                localDateTime = LocalDateTime.parse(startTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                startTime = formatter3.format(zonedDateTime);

                endTime = jsonPathEvaluator.get("EndTime[" + i + "]");
                localDateTime = LocalDateTime.parse(endTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                endTime = formatter3.format(zonedDateTime);

               allString = startTime + "|" + endTime + "|" + activitytype;

                activityLogs.add(allString);
            }

        }
        return activityLogs;
    }

    public List<String> getActivitiesByCampaign(String wsendpoint, String campaign, String token) {
        List<String> activitiesByCampaign = new ArrayList<String>();
        String code = null;
        //String id = null;
        String disabled = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        request.param("campaign", campaign);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityType/ActivityTypeSelectByCampaign");

        int statusCode = response.getStatusCode();
        System.out.println("getActivitiesByCampaign statusCode: " + statusCode);

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");
            for(int i=0; i<jsonResponse.size();i++) {
               code = jsonPathEvaluator.get("Code[" + i + "]");
                disabled = jsonPathEvaluator.get("StartTime[" + i + "]");
                if(disabled == null){
                    activitiesByCampaign.add(code);
                }
            }
        }

        return activitiesByCampaign;

    }

    public void selectAndApproveTicket(String wsendpoint, String ticketid, String token){

        String starTime = null;
        String endTime = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();
        RequestSpecification request2 = RestAssured.given();

        request.param("id", ticketid);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/Ticket/TicketSelectID");
        int statusCode = response.getStatusCode();
        System.out.println("TicketSelectID statusCode: " + statusCode);

        ArrayList<JSONObject> ticketReqArray= new ArrayList<JSONObject>();
        JSONObject ticketActivityLogs = new JSONObject();
        JSONObject requestParams = new JSONObject();

        if(statusCode == 200) {

            JsonPath jsonPathEvaluator = response.jsonPath();
            requestParams.put("ticketID", Integer.parseInt(ticketid));
            requestParams.put("employeeID", jsonPathEvaluator.get("Ticket.EmployeeID").toString());
            requestParams.put("approver" , jsonPathEvaluator.get("Ticket.ApproverEID").toString());
            requestParams.put("approverSub",  jsonPathEvaluator.get("Ticket.ApproverSubEID").toString());
            requestParams.put("ticketStatus", 1);
            requestParams.put("updatedBy", jsonPathEvaluator.get("Ticket.ApproverEID").toString());

            List<String> jsonResponse = response.jsonPath().getList("TicketActivityLogs");
            String start = null;

            for (int i = 0; i < jsonResponse.size(); i++) {
                start = "TicketActivityLogs[" + i + "].";
                Integer Id = jsonPathEvaluator.get("TicketActivityLogs[" + i + "].ID");
                Integer ticketID = jsonPathEvaluator.get(start + "TicketID");
                Integer actionReasonId = jsonPathEvaluator.get(start + "ActionReasonID");
                String actionType = jsonPathEvaluator.get(start + "ActionType");
                Integer activityLogId = jsonPathEvaluator.get(start + "ActivityLogID");
                String employeeId = jsonPathEvaluator.get(start + "EmployeeID");
                Integer activitytypeId =  jsonPathEvaluator.get(start + "ActivityTypeID");

                ticketActivityLogs.put("ID", Id);
                ticketActivityLogs.put("TicketID", ticketID);
                ticketActivityLogs.put("ActionReasonID", actionReasonId);
                ticketActivityLogs.put("ActionType", actionType);
                ticketActivityLogs.put("ActivityLogID", activityLogId);
                ticketActivityLogs.put("EmployeeID", employeeId);
                ticketActivityLogs.put("ActivityTypeID", activitytypeId);

                starTime = jsonPathEvaluator.get(start + "StartTime").toString();
                //starTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd HH:mm:ss", starTime);
                endTime = jsonPathEvaluator.get(start + "EndTime").toString();
                //endTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd HH:mm:ss", endTime);

                ticketActivityLogs.put("StartTime", starTime);
                ticketActivityLogs.put("EndTime", endTime);

                String campaign = jsonPathEvaluator.get(start + "Campaign");
                String lob = jsonPathEvaluator.get(start + "Lob");
                String is1 = jsonPathEvaluator.get(start + "IS1");
                String is2 = jsonPathEvaluator.get(start + "IS2");
                String is3 = jsonPathEvaluator.get(start + "IS3");
                String is4 = jsonPathEvaluator.get(start + "IS4");
                String timezone = jsonPathEvaluator.get(start + "TimeZone");
                String timezonOffset = jsonPathEvaluator.get(start + "TimeZoneOffset");
                String remark = jsonPathEvaluator.get(start + "Remark");
                String createdBy = jsonPathEvaluator.get(start + "CreatedBy");
                String createdDate = jsonPathEvaluator.get(start + "CreatedDate");
                String updatedBy = jsonPathEvaluator.get(start + "CreatedBy");
                String updatedDate = jsonPathEvaluator.get(start + "CreatedDate");


                ticketActivityLogs.put("Campaign", campaign);
                ticketActivityLogs.put("Lob", lob);
                ticketActivityLogs.put("IS1", is1);
                ticketActivityLogs.put("IS1", is2);
                ticketActivityLogs.put("IS3", is3);
                ticketActivityLogs.put("IS4", is4);
                ticketActivityLogs.put("TimeZone", timezone);
                ticketActivityLogs.put("TimeZoneOffset", timezonOffset);
                ticketActivityLogs.put("Remark", remark);
                ticketActivityLogs.put("CreatedBy", createdBy);
                ticketActivityLogs.put("CreatedDate", createdDate);
                ticketActivityLogs.put("UpdatedBy", updatedBy);
                ticketActivityLogs.put("UpdatedDate", updatedDate);
                ticketActivityLogs.put("MachineName", JSONObject.NULL);

                ticketReqArray.add(ticketActivityLogs);
            }

            String ticketEmployeeID = jsonPathEvaluator.get("Ticket.EmployeeID");
            requestParams.put("createdBy", ticketEmployeeID);
            requestParams.put("ticketActivityLogs", ticketReqArray.toString());
            System.out.println("requestParams: [" + requestParams + "]");

            request2.body(requestParams.toString());
            request2.header("Content-Type","application/json");
            request2.header("Authorization", "Bearer " + token);
            response = request2.post("/api/Ticket/TicketApprove");

            statusCode = response.getStatusCode();
            System.out.println("TicketApprove statusCode: " + statusCode);

        }

    }

    public List<String> getActionReasonSelect(String wsendpoint, String token) {
        List<String> actionReasonSelect = new ArrayList<String>();
        String code = null;
        boolean status;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActionReason/ActionReasonSelect");

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++) {
                code = jsonPathEvaluator.get("Code[" + i + "]");
                status = jsonPathEvaluator.get("Status[" + i + "]");
                if(status){ actionReasonSelect.add(code.trim()); }
            }
        }

        return actionReasonSelect;

    }

    public List<Map<Object, String>> getEmployeesUnderASupervisor(String wsendpoint, String token, String employeeId) {
        List<Map<Object, String>> employeeList = new ArrayList<Map<Object, String>>();
        Map<Object, String> rowData = new HashMap<>();
        String employeeID;
        String fullname;
        //String iS1;
        String campaign;
        String lob;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given();

        request.param("employeeID", employeeId);
        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/Employee/EmployeeSelectByIS");

        int statusCode = response.getStatusCode();

        System.out.println("statusCode: " + statusCode);

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++) {
                employeeID = jsonPathEvaluator.get("EmployeeID[" + i + "]");
                fullname = jsonPathEvaluator.get("FullName[" + i + "]");
                //iS1 = jsonPathEvaluator.get("IS1[" + i + "]");
                campaign = jsonPathEvaluator.get("Campaign[" + i + "]");
                lob = jsonPathEvaluator.get("LOB[" + i + "]");

                rowData = new HashMap<>();
                for(int j=1; j<6; j++){
                    if(j == 1)  rowData.put(j, "Edit Logs");
                    if(j == 2)  rowData.put(j, employeeID);
                    if(j == 3)  rowData.put(j, fullname);
                    if(j == 4)  rowData.put(j, campaign);
                    if(j == 5)  rowData.put(j, lob);
                }

                employeeList.add(rowData);
            }
        }

       Collections.sort(employeeList, new Comparator<Map<Object, String>>() {
            @Override
            public int compare(Map<Object, String> map1, Map<Object, String> map2) {
                return map1.get(2).compareTo(map2.get(2));
            }
        });

        return employeeList;

    }

        public List<String> getTicketDetailsGivenTicketNumber(String wsendpoint, String ticketid, String token){

            String starTime = null;
            String endTime = null;
            List<String> logEntries = new ArrayList<>();

            RestAssured.baseURI = wsendpoint;
            RequestSpecification request = RestAssured.given();
            RequestSpecification request2 = RestAssured.given();

            request.param("id", ticketid);

            request.header("Authorization", "Bearer " + token);
            Response response = request.get("/api/Ticket/TicketSelectID");
            int statusCode = response.getStatusCode();
            System.out.println("TicketSelectID statusCode: " + statusCode);


            if(statusCode == 200) {

                JsonPath jsonPathEvaluator = response.jsonPath();
                List<String> jsonResponse = response.jsonPath().getList("TicketActivityLogs");
                String start = "";

                for (int i = 0; i < jsonResponse.size(); i++) {
                    start = "TicketActivityLogs[" + i + "].";
                    String activitytype =  jsonPathEvaluator.get(start + "ActivityType");

                    starTime = jsonPathEvaluator.get(start + "StartTime").toString();
                    starTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd hh:mm a", starTime);
                    endTime = jsonPathEvaluator.get(start + "EndTime").toString();
                    endTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd hh:mm a", endTime);

                    String finalTxt = starTime + "|" + endTime + "|" + activitytype;
                    logEntries.add(finalTxt);

                }
            }
            return logEntries;


        }


    public static void main(String[] args) throws Exception {
        APIHelper helper = new APIHelper();
        /*UserAuthenticate userinfo = helper.getLoginUserInfo("http://10.10.192.60:8090/editv2",
                "ISBI8888001", "TaskUs@2020!");
        int ret = helper.createACompleteActivityLogForAUser("http://10.10.192.60:8090/editv2", userinfo);
        if(ret == 200) {
            UserAuthenticate approverInfo = helper.getLoginUserInfo("http://10.10.192.60:8090/editv2",
                    "ISBI8888002", "TaskUs@2020!");
            Integer ticketid = helper.selectAllActivityForApprovalGivenEmployeeNo("http://10.10.192.60:8090/editv2",
                    approverInfo.getEmployeeID(), approverInfo.getAuthenticationToken());
            System.out.println("ticketid: " + ticketid);
            if(ticketid != 0) {
                helper.selectAndApproveTicket("http://10.10.192.60:8090/editv2",
                        String.valueOf(ticketid), approverInfo.getAuthenticationToken());
            }
        }*/


        //UserAuthenticate approverInfo = helper.getLoginUserInfo("http://10.10.192.60:8090/editv2",
        //        "ISBI8888002", "TaskUs@2020!");
        //helper.selectAndApproveTicket("http://10.10.192.60:8090/editv2",
        //        String.valueOf("742291"), approverInfo.getAuthenticationToken());
        //DBHelper dbHelper = new DBHelper();
        //String createdDate = dbHelper.getDBTimeDateInISO8601Format();
        /*helper.approveSubmittedStartShiftActivity("http://phlbl02dev01:8090/editv2", userinfo.getEmployeeID(),
                userinfo.getAuthenticationToken(), ticketid, userinfo.getFirstSupervisor(), userinfo.getSecondSupervisor(),
                "2020-02-01 08:00:00", userinfo.getCampaignName(),
                userinfo.getTimeZone(), userinfo.getTimeZoneOffset(), userinfo.getUserIdentity(), createdDate);*/

        /*helper.getActivityLogSelectForEdit("http://10.10.192.60:8090/editv2", userinfo.getEmployeeID(),
               "2020-02-02 00:00:00", userinfo.getTimeZone(), userinfo.getTimeZoneIANA(),
                "2020-02-08 23:59:59", userinfo.getAuthenticationToken());*/
        /*List<String> activities = helper.getActivitiesByCampaign("http://10.10.192.60:8090/editv2",
                userinfo.getCampaignName(), userinfo.getAuthenticationToken());
        for(int i=0; i<activities.size(); i++){
            System.out.println(activities.get(i));
        }*/

        UserAuthenticate approverInfo = helper.getLoginUserInfo("http://phlbl02dev01:8090/editv2",
                "ISBI9900263");
        helper.getEmployeesUnderASupervisor("http://phlbl02dev01:8090/editv2",
                approverInfo.getAuthenticationToken(), "9900263");
    }
}
