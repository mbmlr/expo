import { HeadingText } from 'expo-stories/shared/components';
import React from 'react';

import AudioModeSelector from './components/AudioModeSelector';
import Player from './components/AudioPlayer';
import Recorder from './components/Recorder';

export const RecordingExample = () => {
  const [recordingUri, setRecordingUri] = React.useState('');

  const handleRecordingFinished = (recordingUri: string) => setRecordingUri(recordingUri);

  const maybeRenderLastRecording = () =>
    recordingUri ? (
      <>
        <HeadingText>Last recording</HeadingText>
        <Player source={{ uri: recordingUri }} />
      </>
    ) : null;

  return (
    <>
      <HeadingText>Audio mode</HeadingText>
      <AudioModeSelector />
      <HeadingText>Recorder</HeadingText>
      <Recorder onDone={handleRecordingFinished} />
      {maybeRenderLastRecording()}
    </>
  );
};

RecordingExample.storyConfig = {
  name: 'Audio Recording',
};

export default {
  title: 'Recording',
};
