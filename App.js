import { StatusBar } from 'expo-status-bar';
import React, { Component } from 'react';
import backimg from "./image/back.png"
import bannerimage from "./image/bannaimage.png"
import Icon from "react-native-vector-icons/Ionicons";
import {
  StyleSheet,
  Text,
  View,
  Image,
  ImageBackground,
  Dimensions,
} from "react-native";
import { TextInput } from "react-native-gesture-handler";
const { width: WIDTH } = Dimensions.get("screen");
const {height:HEIGHT}=Dimensions.get('screen')
export default class App extends Component {

  constructor() {
    super();
    this.state = {
      dataSource: {},
    };
  }

    render(){
  return (
    <ImageBackground style={styles.container} source={backimg}>
      <StatusBar backgroundColor="white"/>
      <View style={styles.logoContainer}>
        <Text style={styles.logoText}>REACT NATIVE</Text>
      </View>

      <View style={styles.inputContainer}>
        <Icon
          name={"ios-search"}
          size={28}
          color={"rgba(255,255,255,0.7)"}
          style={styles.inputIcon}
        />
        <TextInput
          style={styles.input}
          placeholder={"Search"}
          placeholderTextColor={"rgba(255,255,255,0.7)"}
          underlineColorAndroid="transparent"
        />
      </View>

     <View state={styles.banner}>
      <Image style={styles.bannerImage} source={bannerimage}/>
    </View>

    </ImageBackground>
  );
}
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: WIDTH,
    height: HEIGHT,
    alignItems: "center",
  },

  logoContainer: {
    marginTop: 50,
    position: "absolute",
    marginBottom: 50,
    top: 0,
    alignItems: "center",
  },

  logoText: {
    color: "black",
    fontSize: 20,
    fontWeight: "500",
    marginTop: 10,
    opacity: 0.5,
  },

  inputContainer: {
    marginTop: 10,
    top: 100,

    position: "absolute",
  },

  input: {
    width: WIDTH - 60,
    height: 45,
    marginTop: 20,
    borderRadius: 45,
    fontSize: 16,
    paddingLeft: 46,
    backgroundColor: "rgba(0,0,0,0.35)",
    color: "rgba(255,255,255,0.7)",
    marginHorizontal: 25,
  },

  inputIcon: {
    color: "white",
    position: "absolute",
    top: 25,
    right: 40,
  },

  banner: {
    marginTop: 50,
    marginBottom: 50,
    position: "absolute",
    backgroundColor:'black',
    alignItems: "center",
  },

  bannerImage: {
    height: 300,
    width: WIDTH - 100,
    alignContent: "center",

    top: 200,
  },
});
