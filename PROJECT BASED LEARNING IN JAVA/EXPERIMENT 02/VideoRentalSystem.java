import java.util.ArrayList;
import java.util.Scanner;

class Video {
    private String title;
    private boolean isAvailable;

    public Video(String title) {
        this.title = title;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Error: Video is already rented out.");
        }
    }

    public void returnVideo() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            System.out.println("Error: Video was not rented.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Available: " + (isAvailable ? "Yes" : "No");
    }
}

class VideoStore {
    private ArrayList<Video> inventory;

    public VideoStore() {
        inventory = new ArrayList<>();
    }

    public void addVideo(String title) {
        if (isVideoExist(title)) {
            System.out.println("Error: Video already exists in the inventory.");
            return;
        }
        inventory.add(new Video(title));
        System.out.println("Video added successfully: " + title);
    }

    private boolean isVideoExist(String title) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No videos in inventory.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i));
            }
        }
    }

    public void rentVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null) {
            if (video.isAvailable()) {
                video.rent();
                System.out.println("You rented: " + title);
            } else {
                System.out.println("Video is currently unavailable.");
            }
        } else {
            System.out.println("Error: Video not found in inventory.");
        }
    }

    public void returnVideo(String title) {
        Video video = findVideoByTitle(title);
        if (video != null) {
            if (!video.isAvailable()) {
                video.returnVideo();
                System.out.println("You returned: " + title);
            } else {
                System.out.println("Error: Video was not rented.");
            }
        } else {
            System.out.println("Error: Video not found in inventory.");
        }
    }

    private Video findVideoByTitle(String title) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                return video;
            }
        }
        return null;
    }
}

public class VideoRentalSystem {
    public static void main(String[] args) {
        VideoStore store = new VideoStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Video Rental Store ---");
            System.out.println("1. Add Video");
            System.out.println("2. List Inventory");
            System.out.println("3. Rent Video");
            System.out.println("4. Return Video");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getChoice(scanner);
            if (choice == -1) continue;

            scanner.nextLine();  // Consume newline character
            switch (choice) {
                case 1:
                    System.out.print("Enter video title to add: ");
                    String titleToAdd = scanner.nextLine().trim();
                    store.addVideo(titleToAdd);
                    break;
                case 2:
                    store.listInventory();
                    break;
                case 3:
                    System.out.print("Enter video title to rent: ");
                    String titleToRent = scanner.nextLine().trim();
                    store.rentVideo(titleToRent);
                    break;
                case 4:
                    System.out.print("Enter video title to return: ");
                    String titleToReturn = scanner.nextLine().trim();
                    store.returnVideo(titleToReturn);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getChoice(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();  // Consume the invalid input
            return -1; // Indicate invalid choice
        }
    }
}
