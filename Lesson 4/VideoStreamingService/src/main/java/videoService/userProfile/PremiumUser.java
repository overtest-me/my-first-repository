package videoService.userProfile;

class PremiumUser extends User {

    PremiumUser(int age) {
        super(age);
    }

    public void createUser() {
        hasAccessToBookmarks = true;
        canWatchHD = true;
    }
}
