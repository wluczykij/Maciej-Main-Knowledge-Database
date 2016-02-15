package maciejDB;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TryAndTest extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSecond;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TryAndTest dialog = new TryAndTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TryAndTest() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 46, 86, 89, 97, 0};
		gbl_contentPanel.rowHeights = new int[]{23, 30, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("First");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JButton btnNewButton = new JButton("Third");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 3;
			gbc_btnNewButton.gridy = 0;
			contentPanel.add(btnNewButton, gbc_btnNewButton);
		}
		{
			JCheckBox chckbxNewCheckBox = new JCheckBox("Fourth ");
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
//			gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
//			gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTHWEST;
			gbc_chckbxNewCheckBox.gridx = 3;
			gbc_chckbxNewCheckBox.gridy = 1;
			gbc_chckbxNewCheckBox.gridheight=1;
			gbc_chckbxNewCheckBox.fill = GridBagConstraints.VERTICAL;
			contentPanel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		}
		{
			textFieldSecond = new JTextField();
			textFieldSecond.setAlignmentY(2.0f);
			textFieldSecond.setAlignmentX(2.0f);
			GridBagConstraints gbc_textFieldSecond = new GridBagConstraints();
			gbc_textFieldSecond.anchor = GridBagConstraints.WEST;
			gbc_textFieldSecond.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldSecond.gridx = 2;
			gbc_textFieldSecond.gridy = 2;
			contentPanel.add(textFieldSecond, gbc_textFieldSecond);
			textFieldSecond.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
