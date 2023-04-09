import tester.*;

interface Tweet{
    public boolean isStartOfThreadBy(String author);
    public int totalLikes();
    public String unrollThread();
}

class TextTweet implements Tweet{
    //fields
    String contents;
    int likes;
    String author;

    //constructor 
    TextTweet(String contents, int likes, String author){
        this.contents = contents;
        this.likes = likes;
        this.author = author;
    }

    public boolean isStartOfThreadBy(String author){
        return this.author.equals(author);
    }

    public int totalLikes(){
        return this.likes;
    }

    public String unrollThread(){
        return this.author + '\n' + this.likes + " likes" + '\n' + this.contents + '\n';
    }

}

class ReplyTweet implements Tweet{
    //fields 
    String contents;
    int likes; 
    String author;
    Tweet replyTo;

    //constructor
    ReplyTweet(String contents, int likes, String author, Tweet replyTo){
        this.contents = contents;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }

    public boolean isStartOfThreadBy(String author){
        return this.author.equals(author) && replyTo.isStartOfThreadBy(author);
    }

    public int totalLikes(){
        return this.likes + replyTo.totalLikes();
    }

    public String unrollThread(){
        return replyTo.unrollThread() + this.author + '\n' + this.likes + " likes" + '\n' + this.contents + '\n';
    }
}

class Tweets{
    TextTweet ex1 = new TextTweet("hello kiko", 2,"KikoP");
    TextTweet ex2 = new TextTweet("have a great day", 10, "dr.seuss");

    ReplyTweet ex3 = new ReplyTweet("Good Morning", 5, "kp", ex1);
    ReplyTweet ex4 = new ReplyTweet("hello world", 15, "dr.seuss", ex2);


    boolean testisStartOfThreadBy(Tester t){
        return t.checkExpect(this.ex1.isStartOfThreadBy("KikoP"), true) && 
        t.checkExpect(this.ex1.isStartOfThreadBy("Pkiko"), false) && 
        t.checkExpect(this.ex2.isStartOfThreadBy("kiko"), false) && 
        t.checkExpect(this.ex2.isStartOfThreadBy("dr.seuss"), true) && 
        t.checkExpect(this.ex4.isStartOfThreadBy("dr.seuss"),true) && 
        t.checkExpect(this.ex3.isStartOfThreadBy("kp"),false);
    }

    boolean testtotalLikes(Tester t){
        return t.checkExpect(this.ex1.totalLikes(), 2) && 
        t.checkExpect(this.ex2.totalLikes(), 10) && 
        t.checkExpect(this.ex3.totalLikes(),7) && 
        t.checkExpect(this.ex4.totalLikes(),25);
    } 

    boolean testunrollThread(Tester t){
        return t.checkExpect(this.ex1.unrollThread(), "KikoP" + '\n' + "2 likes" + '\n' + "hello kiko" + '\n') && 
        t.checkExpect(this.ex2.unrollThread(), "dr.seuss" + '\n' + "10 likes" + '\n' + "have a great day" + '\n') && 
        t.checkExpect(this.ex3.unrollThread(), "KikoP" + '\n'+ "2 likes" + '\n' + "hello kiko" + '\n' + "kp" + '\n' + "5 likes" + '\n' + "Good Morning" + '\n') &&
        t.checkExpect(this.ex4.unrollThread(), "dr.seuss" + '\n' + "10 likes" + '\n' + "have a great day" + '\n' + "dr.seuss" + '\n' + "15 likes" + '\n' + "hello world" + '\n');
    }

}
