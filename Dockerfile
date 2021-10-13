FROM gradle:7.5.0-jdk8

ENV WORK_DIR=/home/gradle

WORKDIR ${WORK_DIR}

COPY --chown=gradle . ${WORK_DIR}

RUN gradle wrapper

CMD gradle test --no-daemon