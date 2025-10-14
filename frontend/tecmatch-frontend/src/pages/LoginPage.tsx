import { Container, Row, Col, Form, Button } from "react-bootstrap";
import styles from "./LoginPage.module.css";

const LoginPage = () => {
  return (
    <Container className={styles.loginContainer} fluid>
      <Row>
        <Col>
          <h1 className={`${styles.title} text-shadow`}>App de chat</h1>
          <h2 className={`${styles.subtitle} text`}>Iniciar sesión</h2>

          <Form>
            <Form.Group controlId="formUsername">
              <Form.Label className={styles.formLabel}>Usuario</Form.Label>
              <Form.Control
                type="text"
                placeholder="Correo electrónico"
                className={styles.formInput}
              />
            </Form.Group>

            <Form.Group controlId="formPassword">
              <Form.Label className={styles.formLabel}>Contraseña</Form.Label>
              <Form.Control
                type="password"
                placeholder="ingresa tu contraseña"
                className={styles.formInput}
              />
            </Form.Group>

            <Button variant="light" className={styles.customButton}>
              Ingresar
            </Button>
          </Form>

          <div className={styles.separator}>
            <div className={styles.separatorCircle}></div>
          </div>

          <Button variant="light" className={styles.customButton}>
            ¡Regístrate!
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default LoginPage;
