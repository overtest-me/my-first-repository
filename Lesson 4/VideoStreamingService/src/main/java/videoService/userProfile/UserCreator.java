package videoService.userProfile;

public class UserCreator {

    public static User createNewUser(UserType userType, int age)
    {
        switch(userType) {

            case LIGHT: {
                return new LightUser(age);
            }

            case ADVANCED: {
                return new AdvancedUser(age);
            }

            case PREMIUM: {
                return new PremiumUser(age);
            }

            default: {
                return null;
                //throw new NullPointerException("User wasn't created");
            }
        }
    }
}
