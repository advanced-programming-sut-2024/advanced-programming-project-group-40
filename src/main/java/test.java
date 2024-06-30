//public class test {
//    public ImageView image1;
//    public ImageView image2;
//    public ImageView image3;
//    public ImageView image4;
//    public ImageView image5;
//    public Pane changePain;
//    public Pane mainPain;
//    public Label name;
//    private ArrayList changeArray;
//    private int selectedImage;
//    private boolean isCommander;
//
//    public void changeFaction(MouseEvent mouseEvent) {
//        isCommander = false;
//        change(false);
//    }
//
//    public void changeLeader(MouseEvent mouseEvent) {
//        isCommander = true;
//        change(true);
//    }
//
//    private void change(boolean isCommander) {
//        changePain.setVisible(true);
//        changePain.setDisable(false);
//        mainPain.setDisable(true);
//        image3.requestFocus();
//        changeArray = new ArrayList<>();
//        String selected;
//        File directory;
//        if (isCommander) {
//            directory = new File((GameMenu.class.getResource("/Images/Commander/" + ApplicationController.getCurrentUser().getFaction().getName())).getPath());
//            selected = ApplicationController.getCurrentUser().getCommander().getName();
//        } else {
//            directory = new File((GameMenu.class.getResource("/Images/Factions")).getPath());
//            selected = ApplicationController.getCurrentUser().getFaction().getName();
//        }
//        File[] files = directory.listFiles();
//        assert files != null;
//        int Flag = 0;
//        for (File f : files) {
//            Image image = new Image(f.toURI().toString(), 1, 1, getNameFromFile(f));
//            if (getNameFromFile(f).equals(selected)) selectedImage = Flag;
//            changeArray.add(image);
//            Flag++;
//        }
//        setImageChange(selectedImage);
//    }
//
//    private void setImageChange(int number) {
//        int n = 3 - number;
//        image1.setImage(null);
//        image2.setImage(null);
//        image3.setImage(null);
//        image4.setImage(null);
//        image5.setImage(null);
//        for (Image image : changeArray) {
//            try {
//                Field field = this.getClass().getDeclaredField("image" + (n++));
//                if (n == 4) name.setText(image.getName());
//                field.setAccessible(true);
//                ((ImageView) field.get(this)).setImage(image.getImage());
//                field.setAccessible(false);
//            } catch (NoSuchFieldException | IllegalAccessException ignored) {
//            }
//        }
//    }
//
//    public void done(MouseEvent mouseEvent) {
//        changePain.setVisible(false);
//        changePain.setDisable(true);
//        mainPain.setDisable(false);
//        image3.getParent().requestFocus();
//        if (isCommander) {
//            String toRegex = "select leader " + name.getText();
//            Matcher matcher = Pattern.compile(GameMenuRegex.SELECTLEADER.getRegex()).matcher(toRegex);
//            matcher.matches();
//            GameMenuController.selectLeader(matcher);
//        } else {
//            String toRegex = "select faction -f " + name.getText();
//            Matcher matcher = Pattern.compile(GameMenuRegex.SELECTFACTION.getRegex()).matcher(toRegex);
//            matcher.matches();
//            GameMenuController.selectFaction(matcher);
//            createCards();
//        }
//        changeLabel();
//    }
//
//    public void forward(MouseEvent mouseEvent) {
//        if (selectedImage != changeArray.size() - 1) selectedImage++;
//        setImageChange(selectedImage);
//    }
//
//    public void backward(MouseEvent mouseEvent) {
//        if (selectedImage != 0) selectedImage--;
//        setImageChange(selectedImage);
//    }
//}
