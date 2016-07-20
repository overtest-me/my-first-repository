class BaseTest {

    String name, description;
    String category;

    public String getFullDescription()
    {
        return String.format("Name: %s Description: %s Category: %s ",
                name, description, category);
    }

    // Original method with 1 parameter
    public boolean reportBug(String bugName)
    {
        System.out.println("New issue with the follow information has been reported:");
        System.out.println("Title - " + bugName);
        System.out.println("");
        return true;
    }

    // Overloaded method with 2 parameters and the same returned type
    public boolean reportBug(String bugName, String[] steps)
    {
        System.out.println("New issue with the follow information has been reported:");
        int index = 1;
        System.out.println("Title - " + bugName);
        System.out.println("Steps to reproduce: ");
        for (String step:steps)
        {
            System.out.println(index++ + ". " + step);
        }
        System.out.println("");
        return true;

    }

    // Overloaded method with 2 parameters and different returned type
    public int reportBug(String bugName, Priority bugPriority)
    {
        int id = 150 + (int)(Math.random() * ((999 - 150) + 1));
        System.out.println("New issue with the follow information has been reported:");
        System.out.println("Title - " + bugName + " (ID: " + id + ")");
        System.out.println("Priority: " + bugPriority);
        return id;
    }
}



