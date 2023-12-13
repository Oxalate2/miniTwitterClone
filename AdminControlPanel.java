import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;



public class AdminControlPanel extends JFrame {

    private static AdminControlPanel instance = new AdminControlPanel();
    private JTree UserGroupsTree;
    private JButton addGroupButton;
    private JButton addUserButton;
    private JScrollPane jScrollPane1;
    private JButton openUserView;
    private JButton showGroupTotal;
    private JButton showMsgsTotal;
    private JButton showPosPercent;
    private JButton showUserTotal;
    private JTextField addUserInput;
    private JTextField addGroupInput;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;

    private UserManager userManager = new UserManager();
    private boolean isUser;
    private DefaultMutableTreeNode selectedNode;
    private String currentNodeName;
    private int numOfGroups;
    public String userUpdate = "";

    public AdminControlPanel() {
        initComponents();
    }

    /*public boolean isUser() {
        if (userManager.ifExists(currentNodeName))
            return true;
        else return false;
    }*/

    public void showUserTotal() {
        JOptionPane.showMessageDialog(this, "Total Users: " + userManager.getUsertotal(), "Group Total", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showGroupTotal() {
        JOptionPane.showMessageDialog(this, "Total Groups: " + numOfGroups, "Group Total", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMsgsTotal() {
        int totalNumTweets = TweetManager.getTweetsTotal();
        JOptionPane.showMessageDialog(this, "Total Messages: " + totalNumTweets, "Messages Total", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showPosPercent() {
        JOptionPane.showMessageDialog(this, "Positive Message Percentage: 50%", "Positive Percent", JOptionPane.INFORMATION_MESSAGE);
    }

    public void validateIDs() {
        String valid = "valid";
        for (String ID : userManager.getUserNames())
            if (ID.contains(" "))
                valid = "invalid";
            
        JOptionPane.showMessageDialog(this, "IDs are " + valid, "ID Validation", JOptionPane.ERROR_MESSAGE);
    }

    public void lastUserUpdate() {
        long time = 0;
        for (User u : userManager.getUsers()) {
            if (u.getUpdateTime() > time) {
                time = u.getUpdateTime();
                userUpdate = u.getUserID();
                System.out.print("Updated!!");
            }
        }
        System.out.print("Last user to update is: " + userUpdate + "\n");
    }

    
    @SuppressWarnings("unchecked")                    
    private void initComponents() {

        addUserButton = new JButton();
        addGroupButton = new JButton();
        openUserView = new JButton();
        showUserTotal = new JButton();
        showGroupTotal = new JButton();
        showMsgsTotal = new JButton();
        showPosPercent = new JButton();
        jScrollPane1 = new JScrollPane();
        addGroupInput = new JTextField();
        addUserInput = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Control Panel");

        // Initializing Tree with a listener
        root = new DefaultMutableTreeNode("Root");
        UserGroupsTree = new JTree(root);
        UserGroupsTree.addTreeSelectionListener(e -> {
            TreePath selectedPath = UserGroupsTree.getSelectionPath();
            if (selectedPath != null) {
                Object lastPathComponent = selectedPath.getLastPathComponent();

                if (lastPathComponent instanceof DefaultMutableTreeNode) {
                    selectedNode = (DefaultMutableTreeNode) lastPathComponent;
                    currentNodeName = selectedNode.getUserObject().toString();
                }
            }
        });



        addUserButton.setText("Add User");
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Get user input
                String newUserInput = new String(addUserInput.getText());
                currentNodeName = newUserInput;
                // Create new user object
                User newUser = new User(newUserInput);
                // Add user to userManager list
                userManager.addUser(newUser);
                // Add then update then reload tree model
                model = (DefaultTreeModel) UserGroupsTree.getModel();
                root = (DefaultMutableTreeNode) model.getRoot();
                DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(newUserInput);
                root.add((DefaultMutableTreeNode) newUserNode);
                model.reload(root);
                // Clear text box
                addUserInput.setText("");

                // If adding user to group

            }
        });

        addGroupButton.setText("Add Group");
        addGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /*if (selectedNode.isUser()) {

                }*/
                
                // Get user input
                String newGroup = new String(addGroupInput.getText());
                // Create new group object
                new Group(newGroup);
                // Add then update then reload tree model
                model = (DefaultTreeModel) UserGroupsTree.getModel();
                root = (DefaultMutableTreeNode) model.getRoot();
                DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(newGroup);
                root.add((DefaultMutableTreeNode) newGroupNode);
                model.reload(root);

                // Clear text box
                addGroupInput.setText("");
                numOfGroups++;

                
            }
        });

        openUserView.setText("View User Profile");
        openUserView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new UserControlPanel(currentNodeName).setVisible(true);
            }
        });

        showUserTotal.setText("Show User Total");
        showUserTotal.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showUserTotal();
            }
        });

        showGroupTotal.setText("Last User Update");
        showGroupTotal.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //showGroupTotal();
                lastUserUpdate();
            }
        });

        showMsgsTotal.setText("Show Messages Total");
        showMsgsTotal.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showMsgsTotal();
            }
        });

        showPosPercent.setText("Validate IDs");
        showPosPercent.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //showPosPercent();
                validateIDs();
            }
        });

        jScrollPane1.setViewportView(UserGroupsTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addGroupInput)
                            .addComponent(addUserInput))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(openUserView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showUserTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(showMsgsTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showGroupTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showPosPercent, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addGroupInput, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(openUserView, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showUserTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showGroupTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showMsgsTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showPosPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminControlPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Singleton Implementation
    public static AdminControlPanel getInstance() {
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        return instance;
    }
}




/*public class AdminControlPanel extends JFrame {
    private static JPanel adminPanel;
    private JPanel treeView;
    
    public AdminControlPanel() {
        initialize();
    }

    public void initialize() {
        // Making main admin frame
        setTitle ("Admin Control Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.makeFrameFullSize();
        setLocationRelativeTo(null);
        setVisible(true);

        addtreeView();
        
        addPanel();
    }

    public void addtreeView() {
        treeView = new JPanel();
        treeView.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        treeView.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 200));
        JLabel treeViewLabel = new JLabel("Tree View:");
        treeView.add(treeViewLabel);
        treeView.setBackground(Color.LIGHT_GRAY);
        add(treeView, BorderLayout.WEST);
    }

    public void addPanel() {
        adminPanel = new JPanel();
        adminPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel userIDLabel = new JLabel("UserID - Renard");
        userIDLabel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("User Added!");
            }
        });


        JLabel groupIDLabel = new JLabel("GroupID - Barkada");
        groupIDLabel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));


        JButton addGroupButton = new JButton("Add Group");
        /* addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminControlPanel.createUser();
            }
        });

        JButton openUserViewButton = new JButton("Open User View");
        /* addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminControlPanel.createUser();
            }
        });

        JButton showUserTotal = new JButton("Show User Total");
        showUserTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminControlPanel.showUserTotal();
            }
        });

        JButton showGroupTotal = new JButton("Show Group Total");
        /* addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminControlPanel.createUser();
            }
        });

        JButton showMsgsTotal = new JButton("Show Messages Total");
        /* addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminControlPanel.createUser();
            }
        });

        JButton showPosPercent = new JButton("Show Positive Percentage");
        /* addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminControlPanel.createUser();
            }
        });

        adminPanel.add(userIDLabel);
        adminPanel.add(addUserButton);
        adminPanel.add(groupIDLabel);
        adminPanel.add(addGroupButton);
        adminPanel.add(openUserViewButton);
        adminPanel.add(showUserTotal);
        adminPanel.add(showGroupTotal);
        adminPanel.add(showMsgsTotal);
        adminPanel.add(showPosPercent);
        add(adminPanel);
    }

    // Button Methods
    private static void showUserTotal() {
        int userTotal = 37;
        JOptionPane.showMessageDialog(adminPanel, "Total Users: " + userTotal, "User Total", JOptionPane.INFORMATION_MESSAGE);
    }

    private void makeFrameFullSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
    }
}*/
