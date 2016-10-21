package videoService.userProfile;

class LightUser extends User {

    LightUser(int age) {
        super(age);
    }

    public void createUser() {
        hasAccessToBookmarks = false;
        canWatchHD = false;
    }
}
