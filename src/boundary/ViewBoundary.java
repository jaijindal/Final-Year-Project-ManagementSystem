package boundary;

import utils.MenuIO;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Boundary class for view.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-14
 */
public class ViewBoundary 
{
    public String menuOptions[] = new String[] {};
    public String header = null;
    public boolean quit = false;

    public ViewBoundary() {}

    public ViewBoundary(String menuOptions[]) 
    {
        this.menuOptions = menuOptions;
    }

    public ViewBoundary(String menuOptions[], String header) 
    {
        this.menuOptions = menuOptions;
        this.header = header;
    }

    public void displayMenu() 
    {
        if (header != null) 
        {
            MenuIO.multipleChoiceMenu(this.menuOptions, this.header);
        }
        else 
        {
            MenuIO.multipleChoiceMenu(this.menuOptions);
        }
    }

    public void popOption(int index)
    {   
        String[] newMenuOptions = new String[numOptions()-1];

        System.arraycopy(this.menuOptions, 0, newMenuOptions, 0, index);
        System.arraycopy(this.menuOptions, index + 1, newMenuOptions, index, numOptions() - index - 1);

        this.menuOptions = newMenuOptions;
    }

    public int numOptions()
    {
        return this.menuOptions.length;
    }

    public void setMenuOptions(String menuOptions[]) 
    {
        this.menuOptions = menuOptions;
    }
    
    public void prependMenuOptions(String menuOptions[]) 
    {
        this.menuOptions = Stream.concat(Arrays.stream(menuOptions), Arrays.stream(this.menuOptions)).toArray(String[]::new);
    }

    public void appendMenuOptions(String menuOptions[]) 
    {
        this.menuOptions = Stream.concat(Arrays.stream(this.menuOptions), Arrays.stream(menuOptions)).toArray(String[]::new);
    }

    public void editMenuOptions(int index, String newMenuOption) 
    {
        menuOptions[index] = newMenuOption;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public void quit() 
    {
        this.quit = true;
    }

    public int inputMenuOption() 
    {
        return MenuIO.inputInt(1, numOptions());
    }

    public boolean hasQuit() 
    {
        return this.quit;
    }

    public void runUntilComplete()
    {
        while (!hasQuit()) 
        {
            runMenu();
        }
    }

    public void runMenu() {};
}