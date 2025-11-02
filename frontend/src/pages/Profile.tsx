import {
  Avatar,
  Button,
  Card,
  CardBody,
  Typography,
} from "@material-tailwind/react";
import Footer from "../components/Footer";

export default function Profile() {
  return (
    <>
      <section className="container mx-auto px-8 py-10">
        <Card
          placeholder
          shadow={false}
          className="border border-gray-300 rounded-2xl"
        >
          <CardBody placeholder>
            <div className="flex lg:gap-0 gap-6 flex-wrap justify-between items-center">
              <div className="flex items-center gap-3">
                <Avatar
                  placeholder
                  src="/img/avatar1.jpg"
                  alt="avatar"
                  variant="rounded"
                />
                <div>
                  <Typography placeholder color="blue-gray" variant="h6">
                    Manuel Cortez
                  </Typography>
                  <Typography
                    placeholder
                    variant="small"
                    className="font-normal text-gray-600"
                  >
                    manuel.cortez258835@potros.itson.edu.mx
                  </Typography>
                </div>
              </div>
              <div className="flex flex-wrap items-center gap-2">
                <Button
                  placeholder
                  variant="outlined"
                  className="border-gray-300 flex items-center gap-2"
                >
                  <i className="fa fa-github text-base" />
                  Github
                </Button>
                <Button
                  placeholder
                  variant="outlined"
                  className="border-gray-300 flex items-center gap-2"
                >
                  <i className="fa-brands fa-twitter" />
                  Twitter
                </Button>
                <Button
                  placeholder
                  variant="outlined"
                  className="border-gray-300 flex items-center gap-2"
                >
                  <i className="fa-brands fa-medium" />
                  Medium
                </Button>
              </div>
            </div>
            <Typography
              placeholder
              variant="small"
              className="font-normal text-gray-600 mt-6"
            >
              Passionate UI/UX designer focused on creating intuitive and
              engaging digital experiences. <br /> Driven by design thinking,
              creativity, and a love for problem-solving.
            </Typography>
          </CardBody>
        </Card>
      </section>
      <Footer></Footer>
    </>
  );
}
