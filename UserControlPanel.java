import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UserControlPanel extends JFrame {

    private JLabel FollowingLabel;
    private JTextArea FollowingView;
    private JLabel NewsFeedLabel;
    private JButton followUserButton;
    private JTextField followUserInput;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTextArea newsFeed;
    private JTextArea jTextArea2;
    private JTextArea jTextArea3;
    private JButton postTweetButton;
    private JTextField tweetInput;

    private String userName;
    private User currentUser;
    private TweetManager tweetManager = new TweetManager();
    private long creationTime;
    private long lastUpdate;


    public UserControlPanel(String user) {
        this.userName = user;
        currentUser = new User(user);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle(userName);

        creationTime = System.currentTimeMillis();

        jTextArea2 = new JTextArea();
        jTextArea3 = new JTextArea();
        followUserButton = new JButton();
        tweetInput = new JTextField();
        FollowingLabel = new JLabel();
        jScrollPane2 = new JScrollPane();
        FollowingView = new JTextArea();
        postTweetButton = new JButton();
        NewsFeedLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        newsFeed = new JTextArea();
        followUserInput = new JTextField();

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        System.out.print("User created: " + creationTime + "\n");

        followUserButton.setText("Follow User");
        followUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Get user input
                String followUserInputString = new String(followUserInput.getText());

                // if (UserManager.ifExists(followUserInputString))
                    

                // Clear followUser box
                followUserInput.setText("");
            }
        });
        FollowingLabel.setText("Following");

        FollowingView.setColumns(20);
        FollowingView.setRows(5);
        jScrollPane2.setViewportView(FollowingView);

        postTweetButton.setText("Post Tweet");
        postTweetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Get user input
                String newTweetInput = new String(tweetInput.getText());
                // Create new tweet object
                Tweet newTweet = new Tweet(newTweetInput);
                // Add Tweet to TweetManager list
                tweetManager.addTweet(newTweet);

                // Clear tweetInput box
                tweetInput.setText("");
                // change lastUpdateTime
                lastUpdate = System.currentTimeMillis();
                currentUser.changeUpdateTime(lastUpdate);
                System.out.print("Last update time: " + currentUser.getUpdateTime() + "\n");
                
            }
        });

        NewsFeedLabel.setText("News Feed");

        newsFeed.setColumns(20);
        newsFeed.setRows(5);
        jScrollPane3.setViewportView(newsFeed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(followUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(followUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(FollowingLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewsFeedLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tweetInput, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(postTweetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(followUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(followUserInput))
                .addGap(16, 16, 16)
                .addComponent(FollowingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tweetInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postTweetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(NewsFeedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
}