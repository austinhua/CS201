/**
 * Main for Markov Text Generation Program
 */

//Name: Austin Hua (ah335)
//Course: CompSci 201
//Purpose: Main method for the Markov assignment that initializes the Map Markov model and brings up the GUI for the user to choose inputs

public class MarkovMain {
    public static void main(String[] args){
        IModel model = new WordMarkovModel();
        SimpleViewer view = new SimpleViewer("CompSci 201 Markov Generation", "k count>");
        view.setModel(model);
    }
}
