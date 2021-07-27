import React from 'react';
import { StyleProp, StyleSheet, Text, TextStyle, View } from 'react-native';

function HeadingText(props: {
  style?: StyleProp<TextStyle>;
  children?: string | React.ReactChildren;
}) {
  return (
    <View style={styles.container}>
      <Text style={[styles.headingText, props.style]}>{props.children}</Text>
    </View>
  );
}

export { HeadingText };

const styles = StyleSheet.create({
  container: {
    marginTop: 16,
  },
  headingText: {
    fontWeight: 'bold',
    fontSize: 18,
  },
});
