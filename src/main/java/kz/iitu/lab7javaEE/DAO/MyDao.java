package kz.iitu.lab7javaEE.DAO;

import kz.iitu.lab7javaEE.JavaBean.Comment;
import kz.iitu.lab7javaEE.JavaBean.Post;
import kz.iitu.lab7javaEE.JavaBean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDao {
    private static final String url = "jdbc:postgresql://localhost:5432/lab7JavaEE";
    private static final String user = "admin";
    private static final String password = "admin";

    public static boolean getUserAuthenticate(String username, String userPassword) {
        ArrayList<User> users = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String user_username = resultSet.getString(2);
                    String user_email = resultSet.getString(3);
                    String password = resultSet.getString(4);
//                    User user = new User(id, user_username, password, user_email);
                    User user = new User();
                    user.setId(id);
                    user.setEmail(user_email);
                    user.setPassword(password);
                    user.setName(user_username);
                    users.add(user);
                }
            }
            for (User user: users){
                if (user.getEmail().equals(username)
                        && user.getPassword().equals(userPassword)){
                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public static boolean addNewUser(User newUser) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO users (name, email, password) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, newUser.getName());
                    preparedStatement.setString(2, newUser.getEmail());
                    preparedStatement.setString(3, newUser.getPassword());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public User selectUser(String user_email) {
        User user1 = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM users WHERE email=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user_email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int userId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String userEmail = resultSet.getString(3);
                        String user_password = resultSet.getString(4);
                        user1 = new User();
                        user1.setId(userId);
                        user1.setEmail(userEmail);
                        user1.setPassword(user_password);
                        user1.setName(name);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user1;
    }

    public Post selectOne(int id) {

        Post post = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM post WHERE post_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int postId = resultSet.getInt(1);
                        String theme = resultSet.getString(2);
                        String body = resultSet.getString(3);
                        int like = resultSet.getInt(4);
                        int dislike = resultSet.getInt(5);
                        int userId = resultSet.getInt(6);
                        post = new Post();
                        post.setId(postId);
                        post.setBody(body);
                        post.setTheme(theme);
                        post.setLikes(like);
                        post.setDislikes(dislike);
                        post.setUser_id(userId);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return post;
    }

    public ArrayList<Post> select() {

        ArrayList<Post> posts = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM post");
                while(resultSet.next()){
                    int postId = resultSet.getInt(1);
                    String theme = resultSet.getString(2);
                    String body = resultSet.getString(3);
                    int like = resultSet.getInt(4);
                    int dislike = resultSet.getInt(5);
                    int userId = resultSet.getInt(6);
                    Post post = new Post();
                    post.setId(postId);
                    post.setBody(body);
                    post.setTheme(theme);
                    post.setLikes(like);
                    post.setDislikes(dislike);
                    post.setUser_id(userId);
                    posts.add(post);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return posts;
    }

    public List<?> selectComments(int id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM comment WHERE post_id=" + id);
                while(resultSet.next()){
                    int commentId = resultSet.getInt(1);
                    String commentBody = resultSet.getString(2);
                    int postId = resultSet.getInt(3);
                    String userName = resultSet.getString(4);
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setComment(commentBody);
                    comment.setPost_id(postId);
                    comment.setUser_name(userName);
                    comments.add(comment);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return comments;
    }

    public boolean insert(Comment newComment) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO comment (comment, post_id, user_name) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, newComment.getComment());
                    preparedStatement.setInt(2, newComment.getPost_id());
                    preparedStatement.setString(3, newComment.getUser_name());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public void updatePost(Post post) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE post SET likes = ?, dislikes = ? WHERE post_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, post.getLikes());
                    preparedStatement.setInt(2, post.getDislikes());
                    preparedStatement.setInt(3, post.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public boolean updateEditedPost(Post post) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE post SET theme = ?, body = ? WHERE post_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, post.getTheme());
                    preparedStatement.setString(2, post.getBody());
                    preparedStatement.setInt(3, post.getId());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public boolean deletePost(int id) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "DELETE FROM post WHERE post_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public boolean createPost(Post post) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO post (theme, body, likes, dislikes, user_id) Values (?, ?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, post.getTheme());
                    preparedStatement.setString(2, post.getBody());
                    preparedStatement.setInt(3, post.getLikes());
                    preparedStatement.setInt(4, post.getDislikes());
                    preparedStatement.setInt(5, post.getUser_id());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
