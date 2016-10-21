package videoService.userProfile;

class AdvancedUser extends User {

    AdvancedUser(int age) {
        super(age);
    }

    public void createUser() {
        hasAccessToBookmarks = true;
        canWatchHD = false;
    }
}
