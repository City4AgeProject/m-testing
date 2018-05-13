package com.city4age.mobile.city4age.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Srle on 1/21/2018.
 */

public class ActivityTypes {
    private List<String> listDataHeader;
    private HashMap<String, List<ActivityData>> listDataChild;

    private HashMap<Integer, ActivityData> activityMap;

    public ActivityTypes() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<ActivityData>>();
        activityMap = new HashMap<Integer, ActivityData>();

        // Adding child data
        listDataHeader.add("Motility");
        listDataHeader.add("Physical Activity");
        listDataHeader.add("Basic Activities of Daily Living");
        listDataHeader.add("Instrumental Activities of Daily Living (IADL)");
        listDataHeader.add("Socialization");
        listDataHeader.add("Cultural");

        // Adding child data
        List<ActivityData> Motility = new ArrayList<ActivityData>();

        ActivityData item0101 = new ActivityData();
        item0101.setId(101);
        item0101.setActivity_name("Walking");
        item0101.setActivity_start_date(new Date());
        item0101.setActivity_end_date(new Date());
        item0101.setActivity_description("WALKING AT SLOW OR NORMAL PACE");
        item0101.setActivityEnum("walking");
        Motility.add(item0101);
        activityMap.put(101, item0101);

        ActivityData item0102 = new ActivityData();
        item0102.setId(102);
        item0102.setActivity_name("Climbing stairs");
        item0102.setActivity_start_date(new Date());
        item0102.setActivity_end_date(new Date());
        item0102.setActivity_description("CLIMBING STAIRS UP AND DOWN COUNTED");
        item0102.setActivityEnum("climbing_stairs");
        Motility.add(item0102);
        activityMap.put(102, item0102);

        ActivityData item0103 = new ActivityData();
        item0103.setId(103);
        item0103.setActivity_name("Still/Moving");
        item0103.setActivity_start_date(new Date());
        item0103.setActivity_end_date(new Date());
        item0103.setActivity_description("BEING STILL OR MOVING ANY PART OF THE BODY");
        item0103.setActivityEnum("still-moving");
        Motility.add(item0103);
        activityMap.put(103, item0103);

        ActivityData item0104 = new ActivityData();
        item0104.setId(104);
        item0104.setActivity_name("Moving across rooms");
        item0104.setActivity_start_date(new Date());
        item0104.setActivity_end_date(new Date());
        item0104.setActivity_description("FROM ROOM TO ROOM IN INDOOR ENVIRONMENTS");
        item0104.setActivityEnum("moving_rooms");
        Motility.add(item0104);
        activityMap.put(104, item0104);

        listDataChild.put(listDataHeader.get(0), Motility);

        List<ActivityData> PhysicalActivity = new ArrayList<ActivityData>();

        ActivityData item0201 = new ActivityData();
        item0201.setId(201);
        item0201.setActivity_name("Exercise in place");
        item0201.setActivity_start_date(new Date());
        item0201.setActivity_end_date(new Date());
        item0201.setActivity_description("ANY TYPE OF EXERCISE IN ONE PLACE");
        item0201.setActivityEnum("exercise_place");
        PhysicalActivity.add(item0201);
        activityMap.put(201, item0201);

        ActivityData item0202 = new ActivityData();
        item0202.setId(202);
        item0202.setActivity_name("Bicycle");
        item0202.setActivity_start_date(new Date());
        item0202.setActivity_end_date(new Date());
        item0202.setActivity_description("RIDING THE BICYCLE");
        item0202.setActivityEnum("bicycle");
        PhysicalActivity.add(item0202);
        activityMap.put(202, item0202);

        ActivityData item0203 = new ActivityData();
        item0203.setId(203);
        item0203.setActivity_name("Swimming");
        item0203.setActivity_start_date(new Date());
        item0203.setActivity_end_date(new Date());
        item0203.setActivity_description("SWIMMING UNDER SUPERVISION");
        item0203.setActivityEnum("swimming");
        PhysicalActivity.add(item0203);
        activityMap.put(203, item0203);

        ActivityData item0204 = new ActivityData();
        item0204.setId(204);
        item0204.setActivity_name("Running");
        item0204.setActivity_start_date(new Date());
        item0204.setActivity_end_date(new Date());
        item0204.setActivity_description("RUNNING OR WALKING AT FAST PACE");
        item0204.setActivityEnum("running");
        PhysicalActivity.add(item0204);
        activityMap.put(204, item0204);

        listDataChild.put(listDataHeader.get(1), PhysicalActivity);

        List<ActivityData> BasicActivitiesOfDailyLiving = new ArrayList<ActivityData>();

        ActivityData item0301 = new ActivityData();
        item0301.setId(301);
        item0301.setActivity_name("Bathing and showering");
        item0301.setActivity_start_date(new Date());
        item0301.setActivity_end_date(new Date());
        item0301.setActivity_description("BATHING OR SHOWERING WITHOUT ASSISTANCE");
        item0301.setActivityEnum("bathing-showering");
        BasicActivitiesOfDailyLiving.add(item0301);
        activityMap.put(301, item0301);

        ActivityData item0302 = new ActivityData();
        item0302.setId(302);
        item0302.setActivity_name("Dressing");
        item0302.setActivity_start_date(new Date());
        item0302.setActivity_end_date(new Date());
        item0302.setActivity_description("DRESSING WITHOUT ASSISTANCE");
        item0302.setActivityEnum("dressing");
        BasicActivitiesOfDailyLiving.add(item0302);
        activityMap.put(302, item0302);

        ActivityData item0303 = new ActivityData();
        item0303.setId(303);
        item0303.setActivity_name("Self-feeding");
        item0303.setActivity_start_date(new Date());
        item0303.setActivity_end_date(new Date());
        item0303.setActivity_description("SELF-FEEDING WITHOUT ASSISTANCE");
        item0303.setActivityEnum("self-feeding");
        BasicActivitiesOfDailyLiving.add(item0303);
        activityMap.put(303, item0303);

        ActivityData item0304 = new ActivityData();
        item0304.setId(304);
        item0304.setActivity_name("Personal hygiene and grooming");
        item0304.setActivity_start_date(new Date());
        item0304.setActivity_end_date(new Date());
        item0304.setActivity_description("PERSONAL HYGIENE AND GROOMING WITHOUT ASSISTANCE");
        item0304.setActivityEnum("personal_hygiene");
        BasicActivitiesOfDailyLiving.add(item0304);
        activityMap.put(304, item0304);

        ActivityData item0305 = new ActivityData();
        item0305.setId(305);
        item0305.setActivity_name("Toilet hygiene");
        item0305.setActivity_start_date(new Date());
        item0305.setActivity_end_date(new Date());
        item0305.setActivity_description("TOILET HYGIENE WITHOUT ASSISTANCE");
        item0305.setActivityEnum("toilet_hygiene");
        BasicActivitiesOfDailyLiving.add(item0305);
        activityMap.put(305, item0305);

        ActivityData item0306 = new ActivityData();
        item0306.setId(306);
        item0306.setActivity_name("Going out");
        item0306.setActivity_start_date(new Date());
        item0306.setActivity_end_date(new Date());
        item0306.setActivity_description("GOING OUTSIDE");
        item0306.setActivityEnum("going_out");
        BasicActivitiesOfDailyLiving.add(item0306);
        activityMap.put(306, item0306);

        listDataChild.put(listDataHeader.get(2), BasicActivitiesOfDailyLiving);

        List<ActivityData> InstrumentalActivitiesOfDailyLiving = new ArrayList<ActivityData>();

        ActivityData item0401 = new ActivityData();
        item0401.setId(401);
        item0401.setActivity_name("Preparing food");
        item0401.setActivity_start_date(new Date());
        item0401.setActivity_end_date(new Date());
        item0401.setActivity_description("PREPARING FOOD WITH MINIMAL OR NO ASSISTANCE");
        item0401.setActivityEnum("preparing_food");
        InstrumentalActivitiesOfDailyLiving.add(item0401);
        activityMap.put(401, item0401);

        ActivityData item0402 = new ActivityData();
        item0402.setId(402);
        item0402.setActivity_name("Housekeeping");
        item0402.setActivity_start_date(new Date());
        item0402.setActivity_end_date(new Date());
        item0402.setActivity_description("HOUSEKEEPING WITH MINIMAL OR NO ASSISTANCE");
        item0402.setActivityEnum("housekeeping");
        InstrumentalActivitiesOfDailyLiving.add(item0402);
        activityMap.put(402, item0402);

        ActivityData item0403 = new ActivityData();
        item0403.setId(403);
        item0403.setActivity_name("Doing laundry");
        item0403.setActivity_start_date(new Date());
        item0403.setActivity_end_date(new Date());
        item0403.setActivity_description("DOING LAUNDRY WITH MINIMAL OR NO ASSISTANCE");
        item0403.setActivityEnum("doing_laundry");
        InstrumentalActivitiesOfDailyLiving.add(item0403);
        activityMap.put(403, item0403);

        ActivityData item0404 = new ActivityData();
        item0404.setId(404);
        item0404.setActivity_name("Phone usage");
        item0404.setActivity_start_date(new Date());
        item0404.setActivity_end_date(new Date());
        item0404.setActivity_description("MAKING CALLS WITH PHONE");
        item0404.setActivityEnum("phone_usage");
        InstrumentalActivitiesOfDailyLiving.add(item0404);
        activityMap.put(404, item0404);

        ActivityData item0405 = new ActivityData();
        item0405.setId(405);
        item0405.setActivity_name("New media communication (Skype, Messenger, Facebook, WhatsApp...)");
        item0405.setActivity_start_date(new Date());
        item0405.setActivity_end_date(new Date());
        item0405.setActivity_description("USING SOCIAL COMMUNICATION APPLICATIONS");
        item0405.setActivityEnum("new_media");
        InstrumentalActivitiesOfDailyLiving.add(item0405);
        activityMap.put(405, item0405);

        ActivityData item0406 = new ActivityData();
        item0406.setId(406);
        item0406.setActivity_name("Shopping groceries");
        item0406.setActivity_start_date(new Date());
        item0406.setActivity_end_date(new Date());
        item0406.setActivity_description("SHOPPING GROCERIES WITH MINIMAL OR NO ASSISTANCE");
        item0406.setActivityEnum("shopping_groceries");
        InstrumentalActivitiesOfDailyLiving.add(item0406);
        activityMap.put(406, item0406);

        ActivityData item0407 = new ActivityData();
        item0407.setId(407);
        item0407.setActivity_name("Shopping other");
        item0407.setActivity_start_date(new Date());
        item0407.setActivity_end_date(new Date());
        item0407.setActivity_description("SHOPPING WITH MINIMAL OR NO ASSISTANCE");
        item0407.setActivityEnum("shopping_other");
        InstrumentalActivitiesOfDailyLiving.add(item0407);
        activityMap.put(407, item0407);

        ActivityData item0408 = new ActivityData();
        item0408.setId(408);
        item0408.setActivity_name("Using public transport");
        item0408.setActivity_start_date(new Date());
        item0408.setActivity_end_date(new Date());
        item0408.setActivity_description("INSIDE PUBLIC TRANSPORT");
        item0408.setActivityEnum("public_transport");
        InstrumentalActivitiesOfDailyLiving.add(item0408);
        activityMap.put(408, item0408);

        ActivityData item0409 = new ActivityData();
        item0409.setId(409);
        item0409.setActivity_name("Using own transport");
        item0409.setActivity_start_date(new Date());
        item0409.setActivity_end_date(new Date());
        item0409.setActivity_description("INSIDE OWN TRANSPORT");
        item0409.setActivityEnum("own_transport");
        InstrumentalActivitiesOfDailyLiving.add(item0409);
        activityMap.put(409, item0409);

        ActivityData item0410 = new ActivityData();
        item0410.setId(410);
        item0410.setActivity_name("Taking medication");
        item0410.setActivity_start_date(new Date());
        item0410.setActivity_end_date(new Date());
        item0410.setActivity_description("TAKING MEDICATION WITH MINIMAL OR NO ASSISTANCE");
        item0410.setActivityEnum("taking_medication");
        InstrumentalActivitiesOfDailyLiving.add(item0410);
        activityMap.put(410, item0410);

        listDataChild.put(listDataHeader.get(3), InstrumentalActivitiesOfDailyLiving);

        List<ActivityData> Socialization = new ArrayList<ActivityData>();

        ActivityData item0501 = new ActivityData();
        item0501.setId(501);
        item0501.setActivity_name("Visiting family");
        item0501.setActivity_start_date(new Date());
        item0501.setActivity_end_date(new Date());
        item0501.setActivity_description("VISITING FAMILY AND RELATIVES");
        item0501.setActivityEnum("visiting_family");
        Socialization.add(item0501);
        activityMap.put(501, item0501);

        ActivityData item0502 = new ActivityData();
        item0502.setId(502);
        item0502.setActivity_name("Visiting friends");
        item0502.setActivity_start_date(new Date());
        item0502.setActivity_end_date(new Date());
        item0502.setActivity_description("VISITING FRIENDS AND NEIGHBOURS");
        item0502.setActivityEnum("visiting_friends");
        Socialization.add(item0502);
        activityMap.put(502, item0502);

        ActivityData item0503 = new ActivityData();
        item0503.setId(503);
        item0503.setActivity_name("Attending senior centers");
        item0503.setActivity_start_date(new Date());
        item0503.setActivity_end_date(new Date());
        item0503.setActivity_description("VISITING SENIOR CENTERS");
        item0503.setActivityEnum("senior_centers");
        Socialization.add(item0503);
        activityMap.put(503, item0503);

        ActivityData item0504 = new ActivityData();
        item0504.setId(504);
        item0504.setActivity_name("Attending other social places");
        item0504.setActivity_start_date(new Date());
        item0504.setActivity_end_date(new Date());
        item0504.setActivity_description("VISITING PARKS, HOBBY/SPORT VENUES, RELIGION PLACES...");
        item0504.setActivityEnum("other_social");
        Socialization.add(item0504);
        activityMap.put(504, item0504);

        listDataChild.put(listDataHeader.get(4), Socialization);

        List<ActivityData> Cultural = new ArrayList<ActivityData>();

        ActivityData item0601 = new ActivityData();
        item0601.setId(601);
        item0601.setActivity_name("Visiting culture/entertainment places");
        item0601.setActivity_start_date(new Date());
        item0601.setActivity_end_date(new Date());
        item0601.setActivity_description("VISITING THEATRES, CINEMAS, EXHIBITIONS, CONCERTS...");
        item0601.setActivityEnum("culture-entertainment");
        Cultural.add(item0601);
        activityMap.put(601, item0601);

        ActivityData item0602 = new ActivityData();
        item0602.setId(602);
        item0602.setActivity_name("Watching TV");
        item0602.setActivity_start_date(new Date());
        item0602.setActivity_end_date(new Date());
        item0602.setActivity_description("WATCHING TELEVISION");
        item0602.setActivityEnum("watching_tv");
        Cultural.add(item0602);
        activityMap.put(602, item0602);

        ActivityData item0603 = new ActivityData();
        item0603.setId(603);
        item0603.setActivity_name("Reading");
        item0603.setActivity_start_date(new Date());
        item0603.setActivity_end_date(new Date());
        item0603.setActivity_description("READING BOOKS, NEWSPAPERS...");
        item0603.setActivityEnum("reading");
        Cultural.add(item0603);
        activityMap.put(603, item0603);

        listDataChild.put(listDataHeader.get(5), Cultural);
    }

    public List<String> getListDataHeader() {
        return listDataHeader;
    }

    public HashMap<String, List<ActivityData>> getListDataChild() {
        return listDataChild;
    }

    public HashMap<Integer, ActivityData> getActivityMap() {
        return activityMap;
    }
}
