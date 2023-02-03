
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LEACHApplication extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  private int numberOfNodes;
  private JLabel[] nodeLabels;
  private boolean[] isClusterHead;
  private JButton resetButton, randomButton;

  public LEACHApplication(int numberOfNodes) {
    this.numberOfNodes = numberOfNodes;
    this.nodeLabels = new JLabel[numberOfNodes];
    this.isClusterHead = new boolean[numberOfNodes];

    setTitle("LEACH Clustering");
    setLayout(new GridLayout(numberOfNodes + 1, 1));

    for (int i = 0; i < numberOfNodes; i++) {
      nodeLabels[i] = new JLabel("Node " + i + ": Not CH");
      add(nodeLabels[i]);
    }

    resetButton = new JButton("Reset");
    resetButton.addActionListener(this);
    add(resetButton);

    randomButton = new JButton("Random");
    randomButton.addActionListener(this);
    add(randomButton);

    setSize(400, (numberOfNodes + 1) * 50);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resetButton) {
      resetLEACH();
    } else if (e.getSource() == randomButton) {
      randomLEACH();
    }
  }

  private void randomLEACH() {
    resetLEACH();
    Random random = new Random();

    for (int i = 0; i < numberOfNodes; i++) {
      // checking if a random number between 0 and 1 is bellow T(i)
      if (random.nextDouble() < T(i)) {
        isClusterHead[i] = true;
        nodeLabels[i].setText("Node " + i + ": CH");
      }
    }
  }

  private void resetLEACH() {
    for (int i = 0; i < numberOfNodes; i++) {
      isClusterHead[i] = false;
      nodeLabels[i].setText("Node " + i + ": Not CH");
    }
  }

  private double T(int node) {
	 // parameters
	double P = 0.1;
	int r = 10;
	float En_max= 20;
    Random random = new Random();
	float En_current = random.nextFloat(En_max);
	
	// t(n)
	return (P*En_current)/((1-P*(Math.floorMod(r, (int)(1/P))))*En_max);
  }


  public static void main(String[] args) {
    new LEACHApplication(10);
  }
}